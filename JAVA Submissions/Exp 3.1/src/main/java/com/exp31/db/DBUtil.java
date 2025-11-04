package com.exp31.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static final String JDBC_URL = "jdbc:h2:mem:exp31;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASS = "";

    static {
        try {
            // Load H2 driver
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load H2 driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void initDatabase() {
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            // Create EMPLOYEE table
            st.execute("CREATE TABLE IF NOT EXISTS EMPLOYEE("
                    + "EmpID INT PRIMARY KEY,"
                    + "Name VARCHAR(100),"
                    + "Salary DECIMAL(10,2))");

            // Insert sample employees if not present
            st.execute("MERGE INTO EMPLOYEE (EmpID, Name, Salary) KEY(EmpID) VALUES (1, 'Alice', 50000.00)");
            st.execute("MERGE INTO EMPLOYEE (EmpID, Name, Salary) KEY(EmpID) VALUES (2, 'Bob', 45000.00)");
            st.execute("MERGE INTO EMPLOYEE (EmpID, Name, Salary) KEY(EmpID) VALUES (3, 'Charlie', 55000.00)");

            // Create ATTENDANCE table
            st.execute("CREATE TABLE IF NOT EXISTS ATTENDANCE("
                    + "Id IDENTITY PRIMARY KEY,"
                    + "StudentID VARCHAR(50),"
                    + "AttDate DATE,"
                    + "Status VARCHAR(20))");
        } catch (SQLException e) {
            throw new RuntimeException("Database initialization failed", e);
        }
    }
}