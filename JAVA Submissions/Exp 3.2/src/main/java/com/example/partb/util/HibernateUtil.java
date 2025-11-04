package com.example.partb.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration cfg = new Configuration();
            cfg.configure(); // reads hibernate.cfg.xml from classpath
            // Register annotated entity classes programmatically so Part B works without XML mappings
            try {
                cfg.addAnnotatedClass(Class.forName("com.example.partb.entity.StudentEntity"));
            } catch (ClassNotFoundException e) {
                // If class not found, continue; most likely project not compiled yet.
                System.err.println("Warning: StudentEntity class not found at runtime: " + e.getMessage());
            }
            return cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
