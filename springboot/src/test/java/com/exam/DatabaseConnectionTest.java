package com.exam;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.activation.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class DatabaseConnectionTest {
//    @Autowired
//    private DataSource dataSource;
//
//    @Test
//    public void testConnection() throws SQLException {
//        try (Connection connection = dataSource.getConnection()) {
//            assertNotNull(connection);
//            System.out.println("Connected to the database!");
//        }
//    }
}
