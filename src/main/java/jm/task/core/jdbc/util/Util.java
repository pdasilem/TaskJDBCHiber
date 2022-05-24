package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URI = "jdbc:mysql://127.0.0.1:49154/userstable?permitMysqlScheme";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static Connection connection;
    private static SessionFactory factory = null;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URI, LOGIN, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Unable to set up connection: " + e);
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            // set the properties for our DB
            Properties settings = new Properties();
            settings.setProperty(AvailableSettings.URL, URI);
            settings.setProperty(AvailableSettings.USER, LOGIN);
            settings.setProperty(AvailableSettings.PASS, PASSWORD);
            settings.setProperty(AvailableSettings.DRIVER, DRIVER);
            settings.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect");
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
