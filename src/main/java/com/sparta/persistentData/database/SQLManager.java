package com.sparta.persistentData.database;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

@Getter
public class SQLManager {

    private String url;
    private String username;
    private String password;
    private FileInputStream fileInputStream;
    private Properties properties;
    private Connection connection;

    @SneakyThrows
    public void getDatabaseConnection() {
        this.loadPropertiesData();
        this.loadConnection();
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

    private void loadConnection() {
        try {
            this.connection = DriverManager.getConnection(
                    this.url, this.username, this.password
            );
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}
