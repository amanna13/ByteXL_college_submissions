<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Attendance Portal</title>
</head>
<body>
    <h2>Student Attendance</h2>
    <form method="post" action="/exp31-servlets/attendance">
        <label>Student ID: <input type="text" name="studentId" required /></label><br/>
        <label>Date: <input type="date" name="date" required /></label><br/>
        <label>Status: 
            <select name="status">
                <option value="Present">Present</option>
                <option value="Absent">Absent</option>
                <option value="Late">Late</option>
            </select>
        </label><br/>
        <input type="submit" value="Submit Attendance" />
    </form>
</body>
</html>