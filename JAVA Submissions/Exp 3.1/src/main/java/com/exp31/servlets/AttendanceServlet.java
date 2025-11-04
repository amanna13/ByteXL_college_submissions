package com.exp31.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exp31.db.DBUtil;

public class AttendanceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String dateStr = request.getParameter("date");
        String status = request.getParameter("status");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (studentId == null || dateStr == null || status == null || studentId.trim().isEmpty()) {
                out.println("<html><body><h3>Missing required fields</h3><a href=\"/exp31-servlets/attendance.jsp\">Back</a></body></html>");
                return;
            }

            try (Connection conn = DBUtil.getConnection()) {
                try (PreparedStatement pst = conn.prepareStatement("INSERT INTO ATTENDANCE(StudentID, AttDate, Status) VALUES (?, ?, ?)") ) {
                    pst.setString(1, studentId);
                    pst.setDate(2, Date.valueOf(dateStr));
                    pst.setString(3, status);
                    int cnt = pst.executeUpdate();

                    out.println("<html><body>");
                    if (cnt > 0) {
                        out.println("<h3>Attendance recorded successfully.</h3>");
                        out.println("<p><a href=\"/exp31-servlets/attendance.jsp\">Record another</a></p>");
                    } else {
                        out.println("<h3>Failed to record attendance.</h3>");
                    }
                    out.println("</body></html>");
                }
            } catch (SQLException e) {
                out.println("<html><body><h3>Error: " + escapeHtml(e.getMessage()) + "</h3></body></html>");
            }
        }
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
    }
}
