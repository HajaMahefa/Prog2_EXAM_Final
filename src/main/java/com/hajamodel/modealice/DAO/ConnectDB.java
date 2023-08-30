package com.hajamodel.modealice.DAO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnectDB {


    @Bean
    public Connection getConnection() throws SQLException{
        return  DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/haja_mode",
                "postgres", "azerty"
        );
    }
}
