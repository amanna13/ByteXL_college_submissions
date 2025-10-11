import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "root", "password");
            con.setAutoCommit(false);
            boolean exit = false;
            while(!exit) {
                System.out.println("1.Insert 2.View 3.Update 4.Delete 5.Exit");
                int choice = sc.nextInt();
                switch(choice) {
                    case 1:
                        System.out.print("ProductID: "); int id = sc.nextInt();
                        System.out.print("ProductName: "); String name = sc.next();
                        System.out.print("Price: "); double price = sc.nextDouble();
                        System.out.print("Quantity: "); int qty = sc.nextInt();
                        PreparedStatement ps = con.prepareStatement("INSERT INTO Product VALUES(?,?,?,?)");
                        ps.setInt(1,id); ps.setString(2,name); ps.setDouble(3,price); ps.setInt(4,qty);
                        ps.executeUpdate(); con.commit(); break;
                    case 2:
                        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Product");
                        while(rs.next()) {
                            System.out.println(rs.getInt("ProductID") + " | " + rs.getString("ProductName") + " | " + rs.getDouble("Price") + " | " + rs.getInt("Quantity"));
                        } break;
                    case 3:
                        System.out.print("Update ProductID: "); int upid = sc.nextInt();
                        System.out.print("New Price: "); double nprice = sc.nextDouble();
                        PreparedStatement psu = con.prepareStatement("UPDATE Product SET Price=? WHERE ProductID=?");
                        psu.setDouble(1,nprice); psu.setInt(2,upid); psu.executeUpdate(); con.commit(); break;
                    case 4:
                        System.out.print("Delete ProductID: "); int delid = sc.nextInt();
                        PreparedStatement psd = con.prepareStatement("DELETE FROM Product WHERE ProductID=?");
                        psd.setInt(1,delid); psd.executeUpdate(); con.commit(); break;
                    case 5: exit=true; break;
                }
            }
            con.close();
        } catch(Exception e) { e.printStackTrace(); }
    }
}