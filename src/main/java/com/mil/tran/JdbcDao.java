package com.mil.tran;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDao {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mil_dev?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";

    public boolean validate(String username, String password) {
        // Step 1: Establish a connection to the database
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                // Step 2: Create a Statement using connection object

                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            // Step 3: Execute the query or update query
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Step 4: Process the ResultSet object.
                System.out.println("User found: " + username);
                return true;
            }
        } catch (SQLException e) {
            // Print SQL exception
            printSQLException(e);
            e.printStackTrace();

        }
        return false;
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            return connection;
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return null;

    };

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (ex instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
