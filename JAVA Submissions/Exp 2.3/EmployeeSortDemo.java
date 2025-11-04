import java.util.*;

public class EmployeeSortDemo {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 30, 70000));
        employees.add(new Employee("Bob", 25, 55000));
        employees.add(new Employee("Charlie", 35, 120000));
        employees.add(new Employee("Diana", 28, 95000));
        employees.add(new Employee("Eve", 22, 40000));

        System.out.println("Original list:");
        employees.forEach(System.out::println);
        System.out.println();

        // Sort by name (alphabetically)
        employees.sort(Comparator.comparing(Employee::getName));
        System.out.println("Sorted by name (ascending):");
        employees.forEach(System.out::println);
        System.out.println();

        // Sort by age (ascending)
        employees.sort(Comparator.comparingInt(Employee::getAge));
        System.out.println("Sorted by age (ascending):");
        employees.forEach(System.out::println);
        System.out.println();

        // Sort by salary (descending)
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        System.out.println("Sorted by salary (descending):");
        employees.forEach(System.out::println);
    }
}
