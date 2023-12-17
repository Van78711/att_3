import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionP {

    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "1234";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    private java.sql.Connection connection;
    public ConnectionP() throws SQLException {
        this.connection = createConnectiob();
    }
    private java.sql.Connection createConnectiob() throws SQLException {

        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

    public void execute(String quaery) throws SQLException {
        connection.createStatement().execute(quaery);
    }
    public ResultSet executeQuaery(String quaery) throws SQLException {
        return connection.createStatement().executeQuery(quaery);
    }

}