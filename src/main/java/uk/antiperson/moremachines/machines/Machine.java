package uk.antiperson.moremachines.machines;

import org.bukkit.World;
import org.bukkit.block.Block;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.hologram.Hologram;
import uk.antiperson.moremachines.config.machines.MachineConfig;
import uk.antiperson.moremachines.machines.unit.UnitManager;
import uk.antiperson.moremachines.utils.BasicLocation;

import java.util.UUID;

public abstract class Machine implements AbstractMachine {

    private BasicLocation unitBlock;
    private MachineType type;
    private int level;
    private int range;
    private int tickInterval;
    private int tickLeft;
    private MachineState state;
    private boolean running;
    private UnitManager unitManager;
    MoreMachines mm;
    private Hologram hologram;
    private FuelBlock fuel;
    private UUID uuid;

    public Machine(MoreMachines mm, BasicLocation unitBlock, MachineType type) {
        this.unitBlock = unitBlock;
        this.type = type;
        this.unitManager = new UnitManager(this);
        this.hologram = new Hologram(this);
        this.fuel = new FuelBlock(this);
        this.state = MachineState.PAUSED;
        this.mm = mm;
        this.uuid = UUID.randomUUID();
        this.tickInterval = getConfig().getDefaultTickInterval();
        this.range = getConfig().getDefaultRange();
        this.level = getConfig().getDefaultLevel();
    }

    /**
     * Tick the machine according to the tick interval and level.
     */
    public void tickMachine() {
        getFuel().doFuel();
        if (isRunning()) {
            if (tickLeft == 0) {
                tickLeft = (int) Math.round((double) getTickInterval() / (double) getLevel());
                tick();
            }
            tickLeft--;
        }
    }

    /**
     * Returns the type of machine.
     *
     * @return the type of machine.
     */
    public MachineType getType() {
        return type;
    }

    /**
     * Returns the correct MachineConfig for this Machine.
     *
     * @return the MachineConfig
     */
    public MachineConfig getConfig() {
        return mm.getConfigManager().getMachine(type);
    }

    /**
     * Returns the current level of this machine.
     *
     * @return the current level of this machine.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the current level of this machine.
     *
     * @param level level for this machine to be set to.
     */
    public void setLevel(int level) {
        if (level > getConfig().getMaxLevel()) {
            return;
        }
        this.level = level;
        update();
    }

    /**
     * Returns the block range of this machine
     *
     * @return the block range of this machine.
     */
    public int getRange() {
        return range;
    }

    /**
     * Sets the block range at which this machine will perform its task.
     *
     * @param range the block range at which this machine should perform its task.
     */
    public void setRange(int range) {
        if (range > getConfig().getMaxRange()) {
            return;
        }
        this.range = range;
        update();
    }

    /**
     * Returns the interval at which this machine is performing its actions.
     *
     * @return the interval at which this machine is performing its actions.
     */
    public int getTickInterval() {
        return tickInterval;
    }

    /**
     * Returns the current state of this machine.
     *
     * @return the state of this machine.
     */
    public MachineState getState() {
        return state;
    }

    /**
     * Sets the current state of the machine.
     *
     * @param state state to set the machine to.
     */
    public void setState(MachineState state) {
        if (state == MachineState.FINISHED && getState() != MachineState.WORKING) {
            return;
        }
        this.state = state;
        update();
    }

    /**
     * Returns whether this machine is currently running.
     *
     * @return whether this machine is running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the running status of this machine
     *
     * @param running the running status of this machine.
     */
    public void setRunning(boolean running) {
        this.running = running;
        update();
        if (running) {
            getUnitManager().loadUnits();
            setState(MachineState.WORKING);
        } else {
            getUnitManager().removeAll();
        }
    }

    /**
     * Updates all status displaying objects.
     */
    private void update() {
        getHologram().update();
        mm.getGuiManager().update(this);
    }

    /**
     * Returns an instance of the Hologram class for this machine.
     * A hologram can be used to display current machine status and details.
     *
     * @return an instance of Hologram.
     */
    public Hologram getHologram() {
        return hologram;
    }

    /**
     * Kill all entities related to this machine
     */
    public void destroy() {
        setRunning(false);
        getHologram().remove();
    }

    /**
     * Returns the UnitManager for this machine.
     * The UnitManager contains all the units for this entity.
     * A Unit a collection of entities that show work is being undergone.
     *
     * @return the UnitManager for this machine.
     */
    public UnitManager getUnitManager() {
        return unitManager;
    }

    /**
     * Returns the location of the central machine block.
     *
     * @return the location of the central machine block.
     */
    public BasicLocation getMachineLocation() {
        return unitBlock;
    }

    /**
     * Returns the central machine block.
     *
     * @return the central machine block.
     */
    public Block getMachineBlock() {
        return getMachineLocation().toBukkit().getBlock();
    }

    /**
     * Returns the block which contains fuel.
     *
     * @return the block which contains fuel
     */
    public FuelBlock getFuel() {
        return fuel;
    }

    /**
     * Returns the world this machine is in.
     *
     * @return the world this machine is in.
     */
    public World getWorld() {
        return getMachineLocation().getWorld();
    }

    /**
     * Returns the UUID for this machine.
     *
     * @return the UUID for this machine.
     */
    public UUID getUniqueId() {
        return uuid;
    }
}
