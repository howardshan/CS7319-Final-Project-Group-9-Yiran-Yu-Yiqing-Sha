package Unselected.Subroutine.DataBase;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Interacting with databases
 */
public class GameData {

    private static final String URL = "jdbc:mysql://localhost:3306/FindTheCountryFlag";
    private static final String USER = "root";
    private static final String PASSWORD = "123456a!";


    // Use the method to establish a connection to the database specified by URL, USER, and PASSWORD and return this connection
    // Any code that interacts with the database can call this method to get a connection.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
