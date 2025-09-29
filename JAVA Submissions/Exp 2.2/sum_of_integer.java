import java.util.*;

public class sum_of_integer 
{
    public static int addition(ArrayList<Integer> data) 
    {
        int sum = 0;
        // Use enhanced for-loop (unboxing occurs here)
        for (Integer num : data) 
        {
            sum += num; // unboxing from Integer to int
        }
        return sum;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> data = new ArrayList<>();

        System.out.print("Enter the size of array: ");
        int n = Integer.parseInt(sc.nextLine()); // read as string and parse to int

        for (int i = 0; i < n; i++) 
        {
            System.out.print("Enter element at index " + i + ": ");
            String input = sc.nextLine(); // read input as string
            int num = Integer.parseInt(input); // parse string to int
            data.add(num); // autoboxing from int to Integer
        }

        int ans = addition(data);

        System.out.println("The sum of all numbers is -> " + ans);

        sc.close();
    }
}