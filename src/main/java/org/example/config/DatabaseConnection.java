package org.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DatabaseConnection {
    public static SessionFactory createSessionFactory(){
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, "org.postgresql.Driver");
        properties.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/hibernate_task2");
        properties.setProperty(Environment.USER, "postgres");
        properties.setProperty(Environment.PASS, "postgres");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty(Environment.SHOW_SQL, "true");
        Configuration configuration =new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Book.class);

        return configuration.buildSessionFactory();

    }
    public static EntityManagerFactory createEntityManagerFactory(){
        return createSessionFactory().unwrap(EntityManagerFactory.class);
    }



}
