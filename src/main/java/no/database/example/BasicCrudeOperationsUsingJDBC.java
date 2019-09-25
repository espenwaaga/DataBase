package no.database.example;

import java.sql.*;

class BasicCrudeOperationsUsingJDBC {

    static String username = "root";
    static String password = "root";
    static String dbUrl = "jdbc:mysql://localhost:3306/testschema";
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;

    static void makeJDBCConnection() {
        try {
            // Deprecated to include Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, username, password);
            if (connection != null){
                System.out.println("Connection Established to MYSQL Database");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    static void addDataToDB(String companyName, String address, int totalEmployees, String webSite) {
        try {
            String insertQueryStatement = "INSERT INTO employee VALUES (?,?,?,?)";

            preparedStatement = connection.prepareStatement(insertQueryStatement);
            preparedStatement.setString(1, companyName);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, totalEmployees);
            preparedStatement.setString(4, webSite);

            preparedStatement.executeUpdate();
            System.out.println(companyName + "added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void getDataFromDB() {
        try{
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM employee";

            preparedStatement = connection.prepareStatement(getQueryStatement);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                int employeeCount = resultSet.getInt("EmployeeCount");
                String webSite = resultSet.getString("Website");

                System.out.format("%s, %s, %s, %s\n", name, address, employeeCount, webSite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateRowInDB (int newEmployeeCount, String name){
        try{
            String updateQueryStatement = "Update Employee set EmployeeCount = ? where Name = ?";
            preparedStatement = connection.prepareStatement(updateQueryStatement);
            preparedStatement.setInt(1, newEmployeeCount);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void removeRowInDB (String name) {
        try {
            String removeRowQueryStatement = "Delete from Employee where name = ?";
            preparedStatement = connection.prepareStatement(removeRowQueryStatement);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void cleanOutDB() {
        try {
            String deleteTableQueryStatement = "DELETE FROM employee";
            preparedStatement = connection.prepareStatement(deleteTableQueryStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void closeConnectionDB() throws SQLException {
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
