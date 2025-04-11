package com.exam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamsystemApplicationTests {
    private DataSource dataSource;
    @Test
    public void contextLoads() throws SQLException{
//        try (Connection connection = dataSource.getConnection()) {
//            assertNotNull(connection);
//            System.out.println("Connected to the database!");
//        }
//        }
        String url = "jdbc:mysql://localhost:3306/exam1?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "040027";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

