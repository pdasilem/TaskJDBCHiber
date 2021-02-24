package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URl = "jdbc:mysql://127.0.0.1:3306/userstable";
    private static final String LOGIN = "root";
    private static final String PASSW = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URl, LOGIN, PASSW);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Unable to set up connection: " + e);
        }
        return connection;
    }
}
