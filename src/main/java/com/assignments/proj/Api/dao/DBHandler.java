package com.assignments.proj.Api.dao;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
 class DBHandler {

    private Properties properties;

    private DBHandler() {

        try (FileInputStream fis = new FileInputStream("C:\\Users\\edwan\\IdeaProjects\\AssigmnetHistory\\db.config")) {
            properties = new Properties();

            properties.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected Connection getConnection() throws SQLException {
        String url = properties.getProperty("url");
        //System.out.println(url);
        return DriverManager.getConnection(url, properties.getProperty("user"), properties.getProperty("pass"));


    }
}
