package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Class DBConnection.
 *
 * @author Jean-Aymeric Diet
 */
final class DBConnection {
    /**
     * The instance.
     */
    private static DBConnection INSTANCE = null;

    /**
     * The connection.
     */
    private Connection connection;

    /**
     * Instantiates a new DB connection.
     */
    private DBConnection() {
        this.open();
    }

    /**
     * Gets the single instance of DBConnection.
     *
     * @return single instance of DBConnection
     */
    public static synchronized DBConnection getInstance() {
        if (DBConnection.INSTANCE == null) {
            DBConnection.INSTANCE = new DBConnection();
        }
        return DBConnection.INSTANCE;
    }

    /**
     * Open.
     *
     */
    private void open() {
        final DBProperties dbProperties = new DBProperties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(dbProperties.getUrl(), dbProperties.getLogin(), dbProperties.getPassword());
        } catch (final ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the connection.
     *
     * @return the connection
     */
    Connection getConnection() {
        return this.connection;
    }
}