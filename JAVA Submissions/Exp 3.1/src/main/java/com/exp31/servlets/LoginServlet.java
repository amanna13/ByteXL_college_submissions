package com.exp31.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    // Hardcoded credentials for demo
    private static final String DEMO_USER = "admin";
    private static final String DEMO_PASS = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Login Result</title></head><body>");
            if (username != null && password != null && username.equals(DEMO_USER) && password.equals(DEMO_PASS)) {
                out.printf("<h2>Welcome, %s!</h2>", escapeHtml(username));
                out.println("<p>Login successful.</p>");
                out.println("<p><a href=\"/exp31-servlets/employees\">View Employees</a></p>");
                out.println("<p><a href=\"/exp31-servlets/attendance.jsp\">Attendance Portal</a></p>");
            } else {
                out.println("<h2>Login Failed</h2>");
                out.println("<p>Invalid username or password.</p>");
                out.println("<p><a href=\"/exp31-servlets/login.html\">Try again</a></p>");
            }
            out.println("</body></html>");
        }
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
    }
}
