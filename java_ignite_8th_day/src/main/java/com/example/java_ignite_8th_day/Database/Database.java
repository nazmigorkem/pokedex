package com.example.java_ignite_8th_day.Database;
import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Database {
    private Connection connection;

    private static Database instance;

    private Database() {
        try {
            this.connection = ConnectionPool.getConnection();
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