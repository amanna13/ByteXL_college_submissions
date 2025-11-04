package com.exp31.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.exp31.db.DBUtil;

public class DBInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize the in-memory database and create sample data
        DBUtil.initDatabase();
        System.out.println("DB initialized for Exp31 application");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing for now
    }
}
