package com.sparta.persistentData.database;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

@Getter
public class ConnectionManager {

    private String url;
    private String username;
    private String password;
    private FileInputStream fileInputStream;
    private Properties properties;
    private static Connection connection;

    @SneakyThrows
    public Connection getDatabaseConnection() {
        this.loadPropertiesData();
        return getConnection();
    }
    public void closeDatabaseConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPropertiesData() {
        try {
            this.fileInputStream = new FileInputStream(".db_properties");
            this.properties = new Properties();
            properties.load(fileInputStream);
            this.url = properties.getProperty("db_url");
            this.username = properties.getProperty("db_username");
            this.password = properties.getProperty("db_password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        this.url, this.username, this.password
                );
                connection.setAutoCommit(false);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return connection;
    }
}
