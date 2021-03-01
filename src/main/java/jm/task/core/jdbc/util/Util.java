package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URl = "jdbc:mysql://127.0.0.1:3306/userstable";
    private static final String LOGIN = "root";
    private static final String PASSW = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;
    private static SessionFactory factory = null;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URl, LOGIN, PASSW);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Unable to set up connection: " + e);
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            // set the properties for our DB
            Properties settings = new Properties();
            settings.setProperty(Environment.URL, URl);
            settings.setProperty(Environment.USER, LOGIN);
            settings.setProperty(Environment.PASS, PASSW);
            settings.setProperty(Environment.DRIVER, DRIVER);
            settings.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
//            settings.setProperty(Environment.SHOW_SQL, "true");
//            settings.setProperty(Environment.HBM2DDL_AUTO, "update"); update - create table IF NOT EXIST, create - new table anyway
            // make conf object with settings above and add entity class
            Configuration cfg = new Configuration()
                    .setProperties(settings)
                    .addAnnotatedClass(User.class);
            // make stReg and factory session
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties())
                    .build();
            factory = cfg.buildSessionFactory(serviceRegistry);
        }
        return factory;
    }
}
