package uk.antiperson.moremachines.storage;

import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.storage.method.MysqlStorage;
import uk.antiperson.moremachines.storage.method.SqlliteStorage;

public class StorageManager {

    private MachineStorage storage;
    private MoreMachines mm;

    public StorageManager(MoreMachines mm) {
        this.mm = mm;
    }

    /**
     * Store all loaded machine data.
     */
    public void storeData() {
        storage.storeMachines(mm.getMachineManager().getLoadedMachines());
    }

    /**
     * Load all stored machine data.
     */
    public void loadData() {
        if (storage == null) {
            storage = selectStorage();
        }
        mm.getMachineManager().getLoadedMachines().addAll(storage.loadMachines());
    }

    private MachineStorage selectStorage() {
        switch (mm.getConfigManager().getStorageConfig().getStorageMethod()) {
            case MYSQL:
                return new MysqlStorage(mm);
            case SQLITE:
                return new SqlliteStorage(mm);
        }
        return null;
    }
}
