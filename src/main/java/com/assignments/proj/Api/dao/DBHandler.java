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
    /*static {  // config file paths for each one
          final String CONFIG_PATH_A ="";
          final String CONFIG_PATH_O ="";
          final String CONFIG_PATH_M ="";
          final String CONFIG_PATH_T ="";
    }*/

    private DBHandler() {

        try (FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\IdeaProjects\\AssigmnetHistory\\src\\main\\java\\com\\assignments\\proj\\Api\\dao\\db.config")) {
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
