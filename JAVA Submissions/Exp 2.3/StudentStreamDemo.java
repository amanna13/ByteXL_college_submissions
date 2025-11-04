import java.util.*;
import java.util.stream.*;

public class StudentStreamDemo {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 82.5),
            new Student("Bob", 67.0),
            new Student("Charlie", 91.0),
            new Student("Diana", 76.5),
            new Student("Eve", 74.0),
            new Student("Frank", 88.0)
        );

        System.out.println("All students:");
        students.forEach(System.out::println);
        System.out.println();

        // Filter students with marks > 75, sort by marks ascending, and display names
        List<String> passedNames = students.stream()
            .filter(s -> s.getMarks() > 75.0)
            .sorted(Comparator.comparingDouble(Student::getMarks))
            .map(Student::getName)
            .collect(Collectors.toList());

        System.out.println("Students with marks > 75 (sorted by marks):");
        passedNames.forEach(System.out::println);
    }
}
