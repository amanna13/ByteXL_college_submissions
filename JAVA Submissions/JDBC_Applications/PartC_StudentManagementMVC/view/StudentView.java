import java.util.Scanner;
public class StudentView {
    Scanner sc = new Scanner(System.in);
    public int menu() {
        System.out.println("1.Add 2.View 3.Update 4.Delete 5.Exit");
        return sc.nextInt();
    }
    public int getStudentID() { System.out.print("StudentID: "); return sc.nextInt(); }
    public String getName() { System.out.print("Name: "); return sc.next(); }
    public String getDepartment() { System.out.print("Department: "); return sc.next(); }
    public double getMarks() { System.out.print("Marks: "); return sc.nextDouble(); }
}