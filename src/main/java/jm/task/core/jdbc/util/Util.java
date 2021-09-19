package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class Util {
    private static final String HOST = "jdbc:mysql://localhost:3306/users?useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Rootroot";

    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            HashMap<String, String> map = new HashMap<>();
            map.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/users");
            map.put("hibernate.connection.username", "root");
            map.put("hibernate.connection.password", "Rootroot");
            map.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

            registryBuilder.applySettings(map);
            StandardServiceRegistry registry = registryBuilder.build();
            MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(User.class);
            sessionFactory = sources.buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
