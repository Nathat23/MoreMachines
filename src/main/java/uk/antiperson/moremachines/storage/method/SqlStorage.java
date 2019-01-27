package uk.antiperson.moremachines.storage.method;

import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.machines.Machine;
import uk.antiperson.moremachines.machines.MachineType;
import uk.antiperson.moremachines.storage.MachineStorage;
import uk.antiperson.moremachines.utils.BasicLocation;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public abstract class SqlStorage implements MachineStorage {

    private Connection connection;
    private String url;
    private String username;
    private String password;
    private MoreMachines mm;

    public SqlStorage(MoreMachines mm, String url) {
        this.mm = mm;
        this.url = url;
    }

    public SqlStorage(MoreMachines mm, String url, String username, String password) {
        this.mm = mm;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = makeConnection();
            createTable();
        }
        return connection;
    }

    private Connection makeConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS DATA (Location VARCHAR(40) PRIMARY KEY,Type VARCHAR(15),Level INT NOT NULL,Radius INT NOT NULL)");
    }

    @Override
    public void storeMachines(Set<Machine> machines) {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("DROP TABLE DATA");
            createTable();
            PreparedStatement prepared = getConnection().prepareStatement("INSERT INTO DATA VALUES (?, ?, ?, ?)");
            for (Machine machine : machines) {
                prepared.setString(1, machine.getMachineLocation().toString());
                prepared.setString(2, machine.getType().toString());
                prepared.setInt(3, machine.getLevel());
                prepared.setInt(4, machine.getRange());
                prepared.addBatch();
            }
            prepared.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    @Override
    public Set<Machine> loadMachines() {
        Set<Machine> machines = new HashSet<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM DATA");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BasicLocation location = BasicLocation.fromString(resultSet.getString(1));
                MachineType type = MachineType.valueOf(resultSet.getString(2));
                Machine machine = mm.getMachineManager().registerMachine(location, type);
                machine.setLevel(resultSet.getInt(3));
                machine.setRange(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return machines;
    }
}
