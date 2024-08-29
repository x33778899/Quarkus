package org.example.service;


import com.jetbrains.exported.JBRApi;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Singleton
public class LoggingService {

    @Inject
    @Named("log_db")
    private DataSource dataSource;

    public void log(String methodName, String message) {
        String sql = "INSERT INTO method_logs (method_name, log_message) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, methodName);
            statement.setString(2, message);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception as needed
        }
    }
}