package com.example.java_ignite_8th_day;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Database {
    private Connection connection;
    private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/test";

    private static Database instance;

    private Database() {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            this.connection = DriverManager.getConnection(CONNECTION_URL, "root", "12341234");
            this.connection.setAutoCommit(false);
            System.out.println("Connection established with database.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static Database createNewInstance() {
        return instance = new Database();
    }

    public void closeConnection() {
        try {
            instance.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Database getInstance() {
        return Objects.requireNonNullElseGet(instance, Database::createNewInstance);
    }

    public Connection getConnection() {
        return connection;
    }
}