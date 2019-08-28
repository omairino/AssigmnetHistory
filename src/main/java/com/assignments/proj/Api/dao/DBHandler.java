package com.assignments.proj.Api.dao;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
@Service
public class DBHandler {

    private Properties properties;
    private Connection connection = null;

    private DBHandler() {

        try (FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\IdeaProjects\\AssigmnetHistory\\src\\main\\java\\com\\assignments\\proj\\Api\\dao\\db.config")) {
            properties = new Properties();

            properties.load(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            newConnection();
        } else if (connection.isClosed()) {
            newConnection();
        }

        return connection;

    }

    private void newConnection() throws SQLException {
        String url = properties.getProperty("url");
        System.out.println(url);
        connection = DriverManager.getConnection(url, properties.getProperty("user"), properties.getProperty("pass"));
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
