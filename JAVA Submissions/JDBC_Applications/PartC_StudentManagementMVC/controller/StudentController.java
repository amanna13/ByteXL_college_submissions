import java.sql.*;
public class StudentController {
    private Connection con;
    public StudentController() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb","root","password");
    }
    public void addStudent(Student s) throws Exception {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Student VALUES(?,?,?,?)");
        ps.setInt(1, s.getStudentID()); ps.setString(2,s.getName()); ps.setString(3,s.getDepartment()); ps.setDouble(4,s.getMarks()); ps.executeUpdate();
    }
    public ResultSet viewStudents() throws Exception {
        return con.createStatement().executeQuery("SELECT * FROM Student");
    }
    public void updateStudent(Student s) throws Exception {
        PreparedStatement ps = con.prepareStatement("UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?");
        ps.setString(1, s.getName()); ps.setString(2,s.getDepartment()); ps.setDouble(3,s.getMarks()); ps.setInt(4,s.getStudentID()); ps.executeUpdate();
    }
    public void deleteStudent(int id) throws Exception {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Student WHERE StudentID=?");
        ps.setInt(1,id); ps.executeUpdate();
    }
}