package no.database.example;

import java.sql.*;

import static no.database.example.BasicCrudeOperationsUsingJDBC.*;
public class MySQLDBExample {

    private static String username = "root";
    private static String password = "root";
    private static String dbUrl = "jdbc:mysql://localhost:3306/testschema";
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;


    public static void main(String[] args) throws SQLException {
        System.out.println("------------------ Connecting to the MySQL DataBase ------------------");
        makeJDBCConnection();

        System.out.println("\n------------------ Clearing the table corresponding to 'employee' first  ------------------");
        cleanOutDB();

        System.out.println("\n------------------ Adding entries to the table called employee ------------------");
        addDataToDB("Crunchify, LLC.", "NYC, US", 5, "https://crunchify.com");
        addDataToDB("Google Inc.", "Mountain View, CA, US", 50000, "https://google.com");
        addDataToDB("Apple Inc.", "Cupertino, CA, US", 30000, "http://apple.com");

        System.out.println("\n------------------ Data in the employee table: ------------------");
        getDataFromDB();

        closeConnectionDB();
    }
}


