import java.util.*;
import static java.util.stream.Collectors.*;

public class ProductStreamDemo {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.0, "Electronics"),
            new Product("Smartphone", 800.0, "Electronics"),
            new Product("Headphones", 150.0, "Electronics"),
            new Product("Bananas", 1.2, "Grocery"),
            new Product("Rice", 40.0, "Grocery"),
            new Product("Olive Oil", 12.5, "Grocery"),
            new Product("Desk Chair", 200.0, "Furniture"),
            new Product("Dining Table", 550.0, "Furniture")
        );

        System.out.println("Products:");
        products.forEach(System.out::println);
        System.out.println();

        // Group products by category
        Map<String, List<Product>> byCategory = products.stream()
            .collect(groupingBy(Product::getCategory));
        System.out.println("Grouped by category:");
        byCategory.forEach((cat, list) -> {
            System.out.println(cat + ": " + list);
        });
        System.out.println();

        // Find the most expensive product in each category
        Map<String, Optional<Product>> mostExpensiveByCat = products.stream()
            .collect(groupingBy(Product::getCategory,
                    maxBy(Comparator.comparingDouble(Product::getPrice))));
        System.out.println("Most expensive product in each category:");
        mostExpensiveByCat.forEach((cat, opt) -> {
            System.out.println(cat + ": " + opt.orElse(null));
        });
        System.out.println();

        // Average price of all products
        double avgPrice = products.stream()
            .collect(averagingDouble(Product::getPrice));
        System.out.printf("Average price of all products: %.2f%n", avgPrice);
    }
}
