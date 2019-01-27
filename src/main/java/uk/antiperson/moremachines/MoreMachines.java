package uk.antiperson.moremachines;

import org.bukkit.plugin.java.JavaPlugin;
import uk.antiperson.moremachines.commands.CommandManager;
import uk.antiperson.moremachines.compat.CompatManager;
import uk.antiperson.moremachines.config.ConfigManager;
import uk.antiperson.moremachines.gui.GuiManager;
import uk.antiperson.moremachines.item.ItemHandler;
import uk.antiperson.moremachines.item.RecipeHandler;
import uk.antiperson.moremachines.listeners.*;
import uk.antiperson.moremachines.storage.StorageManager;
import uk.antiperson.moremachines.tasks.TickUnits;
import uk.antiperson.moremachines.machines.MachineManager;

public class MoreMachines extends JavaPlugin {

    private MachineManager machineManager;
    private ConfigManager configManager;
    private GuiManager guiManager;
    private ItemHandler itemHandler;
    private RecipeHandler recipeHandler;
    private StorageManager storageManager;
    private CommandManager commandManager;
    private CompatManager compatManager;

    @Override
    public void onEnable() {
        getLogger().info("MoreMachines " + getVersion() + " by antiPerson");
        getLogger().info("Get more info at " + getWebsite());

        // Main initialisation
        configManager = new ConfigManager(this);
        machineManager = new MachineManager(this);
        guiManager = new GuiManager();
        itemHandler = new ItemHandler(this);
        recipeHandler = new RecipeHandler(this);
        storageManager = new StorageManager(this);
        commandManager = new CommandManager(this);
        compatManager = new CompatManager(this);

        getLogger().info("Loading machine data from storage...");
        getStorageManager().loadData();
        getLogger().info("Registering machine recipes...");
        getRecipeHandler().registerRecipes();
        getLogger().info("Registering protection plugin hooks...");
        getCompatManager().registerAll();
        getLogger().info("Registering events and starting tasks...");
        getServer().getPluginManager().registerEvents(new FurnaceListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new MachineListener(this), this);
        new TickUnits(this).runTaskTimer(this, 0, 1);
        getCommandManager().registerCommands();
    }

    @Override
    public void onDisable() {
        getStorageManager().storeData();
        getMachineManager().removeAll();
    }

    public String getVersion() {
        return getDescription().getVersion();
    }

    public String getWebsite() {
        return getDescription().getWebsite();
    }

    public MachineManager getMachineManager() {
        return machineManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public GuiManager getGuiManager() {
        return guiManager;
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

    public RecipeHandler getRecipeHandler() {
        return recipeHandler;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public CompatManager getCompatManager() {
        return compatManager;
    }
}
