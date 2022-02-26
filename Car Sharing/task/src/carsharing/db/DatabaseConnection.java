package carsharing.db;

import java.sql.*;

public class DatabaseConnection {
    private static String dbStringURL;

    public static void setDbStringURL(String fileName) {
        dbStringURL = "jdbc:h2:./src/carsharing/db/" + fileName;
    }

    private static  Class driver;


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
}
