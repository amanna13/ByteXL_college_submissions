public class Main {
    public static void main(String[] args) {
        try {
            StudentView view = new StudentView();
            StudentController ctrl = new StudentController();
            boolean exit=false;
            while(!exit) {
                int choice = view.menu();
                switch(choice) {
                    case 1: ctrl.addStudent(new Student(view.getStudentID(), view.getName(), view.getDepartment(), view.getMarks())); break;
                    case 2: java.sql.ResultSet rs = ctrl.viewStudents(); while(rs.next()) {
                        System.out.println(rs.getInt("StudentID") + " | " + rs.getString("Name") + " | " + rs.getString("Department") + " | " + rs.getDouble("Marks"));
                    } break;
                    case 3: ctrl.updateStudent(new Student(view.getStudentID(), view.getName(), view.getDepartment(), view.getMarks())); break;
                    case 4: ctrl.deleteStudent(view.getStudentID()); break;
                    case 5: exit=true; break;
                }
            }
        } catch(Exception e) { e.printStackTrace(); }
    }
}