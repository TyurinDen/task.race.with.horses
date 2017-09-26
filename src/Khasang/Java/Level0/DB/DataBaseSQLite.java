package Khasang.Java.Level0.DB;

import java.sql.*;

public class DataBaseSQLite {
    private final String DB_DRIVER = "org.sqlite.JDBC";
    private String dataBase;
    private String dbUser;
    private String dbPassword;
    private Connection dbConnection = null;

    public DataBaseSQLite(String dataBase, String dbUser, String dbPassword) {
            registerDBDriver(DB_DRIVER);
            this.dataBase = dataBase;
            this.dbUser = dbUser;
            this.dbPassword = dbPassword;
    }

    private void registerDBDriver(String DBDriver) {
        try {
            Class.forName(DBDriver);
            System.out.println(DBDriver + " driver Registered!");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC driver " + DBDriver + " not found");
            System.err.println(e.getMessage());
        }
    }

    public Connection getDBConnection() {
        if (dbConnection != null) {
            System.out.println("Connection status \"OK\". Take control...");
            return dbConnection;
        }

        try {
            dbConnection = DriverManager.getConnection(dataBase, dbUser, dbPassword);
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            System.err.println(e.getMessage());
        }

        if (dbConnection != null) {
            System.out.println("Connection status \"OK\". Take control...");
        } else {
            System.err.println("Failed to make connection");
        }
        return dbConnection;
    }

    public Statement getStatement(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Something wrong with the Connection or Statement...");
            e.printStackTrace();
        }
        return statement;
    }
}
