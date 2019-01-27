package uk.antiperson.moremachines.storage.method;

import uk.antiperson.moremachines.MoreMachines;

public class SqlliteStorage extends SqlStorage {

    public SqlliteStorage(MoreMachines mm) {
        super(mm, "jdbc:sqlite:" + mm.getDataFolder().getPath() + "/database.db");
    }
}
