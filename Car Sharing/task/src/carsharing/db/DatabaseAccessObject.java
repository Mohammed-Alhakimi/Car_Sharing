package carsharing.db;

import java.sql.*;

public class DatabaseAccessObject {
    private static String dbStringURL;

    public static void setDbStringURL(String fileName) {
        DatabaseAccessObject.dbStringURL = "jdbc:h2:./src/carsharing/db/" + fileName;
    }

    private static Class driver;


    static {
        dbStringURL = "jdbc:h2:./src/carsharing/db/test.db";
        try {
            driver = Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }

    /**
     * @return Returns a Valid connection with the database
     */
    public static Connection connect() {
        try {
            return DriverManager.getConnection(dbStringURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a table called Company
     */
    public static void createCompanyTable() {
        try (Connection connection = DatabaseAccessObject.connect()) {
            assert connection != null;
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(true);
                String query = " CREATE TABLE IF NOT EXISTS COMPANY (" +
                        "ID INTEGER NOT NULL PRIMARY KEY," +
                        "NAME VARCHAR(60)" +
                        "); ";
                statement.executeUpdate(query);
            }
        } catch (SQLException throwables) {
            System.out.println("Table Already exists");
        }
    }
}
