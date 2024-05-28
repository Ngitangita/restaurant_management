package com.example.restorationmanagement.connection;

import com.example.restorationmanagement.exception.DatabaseException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@Configuration
public class ConnectionBDD {
        @Value("${postgresql.database.url}")
        private String url;


        @Value("${postgresql.database.username}")
        private String username;


        @Value("${postgresql.database.password}")
        private String password;

        @Bean
        public Connection getConnection() {
            try {
                return DriverManager.getConnection(
                        url,
                        username,
                        password
                );
            } catch (SQLException e) {
                throw new DatabaseException("Error connecting to database", e);
            }
        }
    }

