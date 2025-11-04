package com.exp31.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exp31.db.DBUtil;

public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Employees</title></head><body>");
            out.println("<h2>Employee Records</h2>");
            out.println("<form method=\"get\" action=\"/exp31-servlets/employees\">");
            out.println("Search by EmpID: <input type=\"text\" name=\"id\" /> <input type=\"submit\" value=\"Search\" />");
            out.println("</form>");

            try (Connection conn = DBUtil.getConnection()) {
                if (idParam == null || idParam.trim().isEmpty()) {
                    // list all
                    try (java.sql.PreparedStatement pst = conn.prepareStatement("SELECT EmpID, Name, Salary FROM EMPLOYEE ORDER BY EmpID");
                         java.sql.ResultSet rs = pst.executeQuery()) {
                        renderTable(rs, out);
                    }
                } else {
                    // search by id
                    try (java.sql.PreparedStatement pst = conn.prepareStatement("SELECT EmpID, Name, Salary FROM EMPLOYEE WHERE EmpID = ?")) {
                        pst.setInt(1, Integer.parseInt(idParam));
                        try (java.sql.ResultSet rs = pst.executeQuery()) {
                            if (rs.next()) {
                                out.println("<table border=\"1\"><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
                                out.printf("<tr><td>%d</td><td>%s</td><td>%.2f</td></tr>", rs.getInt("EmpID"), escapeHtml(rs.getString("Name")), rs.getDouble("Salary"));
                                out.println("</table>");
                            } else {
                                out.println("<p>No employee found with ID " + escapeHtml(idParam) + "</p>");
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                out.println("<p>Error accessing database: " + escapeHtml(e.getMessage()) + "</p>");
            }

            out.println("</body></html>");
        }
    }

    private void renderTable(ResultSet rs, PrintWriter out) throws SQLException {
        out.println("<table border=\"1\"><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
        while (rs.next()) {
            out.printf("<tr><td>%d</td><td>%s</td><td>%.2f</td></tr>", rs.getInt("EmpID"), escapeHtml(rs.getString("Name")), rs.getDouble("Salary"));
        }
        out.println("</table>");
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
    }
}
