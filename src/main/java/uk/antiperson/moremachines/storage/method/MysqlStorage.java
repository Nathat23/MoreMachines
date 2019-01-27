package uk.antiperson.moremachines.storage.method;

import uk.antiperson.moremachines.MoreMachines;

public class MysqlStorage extends SqlStorage {

    public MysqlStorage(MoreMachines mm) {
        super(mm, "jdbc:mysql://" + mm.getConfigManager().getStorageConfig().getAddress(),
                mm.getConfigManager().getStorageConfig().getUsername(),
                mm.getConfigManager().getStorageConfig().getPassword());
    }
}
