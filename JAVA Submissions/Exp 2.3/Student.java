public class Student {
    private String name;
    private double marks; // percentage

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
    public String getName() { return name; }
    public double getMarks() { return marks; }
    @Override
    public String toString() {
        return String.format("Student{name='%s', marks=%.2f}", name, marks);
    }
}
