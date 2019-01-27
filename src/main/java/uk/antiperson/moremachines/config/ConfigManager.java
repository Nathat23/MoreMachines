package uk.antiperson.moremachines.config;

import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.config.machines.MachineConfig;
import uk.antiperson.moremachines.machines.MachineType;

import java.util.EnumMap;

public class ConfigManager {

    private MoreMachines mm;
    private GeneralConfig generalConfig;
    private EnumMap<MachineType, MachineConfig> map;

    public ConfigManager(MoreMachines mm) {
        this.mm = mm;
        this.map = new EnumMap<>(MachineType.class);
        this.generalConfig = new GeneralConfig(this);
        for (MachineType mt : MachineType.values()) {
            map.put(mt, new MachineConfig(this, mt));
        }
    }

    /**
     * Gets the ConfigFile for this file.
     *
     * @param folder   the folder location of this file.
     * @param fileName the file name of this file
     * @return the ConfigFile for this file.
     */
    public ConfigFile getConfig(String folder, String fileName) {
        return new ConfigFile(mm, folder, fileName);
    }

    /**
     * Gets the applicable MachineConfig for this MachineType.
     *
     * @param type the type of machine.
     * @return the applicable MachineConfig.
     */
    public MachineConfig getMachine(MachineType type) {
        return map.get(type);
    }

    public ConfigWrapper getSpecificConfig(String filePath) {
        return new ConfigWrapper(mm, filePath);
    }

    public GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

    public StorageConfig getStorageConfig() {
        return getGeneralConfig().getStorageConfig();
    }
}
