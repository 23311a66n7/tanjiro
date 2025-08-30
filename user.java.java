package jdbc2;
import java.sql.*;
import java.util.Scanner;

public class user {
    public static void main(String[] args) {
        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "root", "root");
            Scanner obj = new Scanner(System.in);

            System.out.println("1. Insert\n2. Update\n3. Delete\nEnter choice: ");
            int choice = obj.nextInt();
            obj.nextLine();  // Consume the newline character left by nextInt()

            if (choice == 1) {
                System.out.print("Enter ID: ");
                String id = obj.nextLine();  // Changed to nextLine to handle string IDs
                System.out.print("Enter Name: ");
                String name = obj.nextLine();
                System.out.print("Enter Password: ");
                String password = obj.nextLine();

                PreparedStatement pst = c.prepareStatement("INSERT INTO employee (user_id, user_name, password) VALUES (?, ?, ?)");
                pst.setString(1, id);  // Set ID as a String
                pst.setString(2, name);
                pst.setString(3, password);
                pst.executeUpdate();
                System.out.println("Inserted successfully.");
            } 
            else if (choice == 2) {
                System.out.print("Enter ID: ");
                String id = obj.nextLine();  // Changed to nextLine to handle string IDs
                System.out.print("Enter New Password: ");
                String password = obj.nextLine();

                PreparedStatement pst = c.prepareStatement("UPDATE employee SET password = ? WHERE user_id = ?");
                pst.setString(1, password);
                pst.setString(2, id);  // Set ID as a String
                int rows = pst.executeUpdate();
                System.out.println(rows > 0 ? "Updated successfully." : "User not found.");
            } 
            else if (choice == 3) {
                System.out.print("Enter ID to delete: ");
                String id = obj.nextLine();  // Changed to nextLine to handle string IDs

                PreparedStatement pst = c.prepareStatement("DELETE FROM employee WHERE user_id = ?");
                pst.setString(1, id);  // Set ID as a String
                int rows = pst.executeUpdate();
                System.out.println(rows > 0 ? "Deleted successfully." : "User not found.");
            } 
            else {
                System.out.println("Invalid choice.");
            }

            c.close();
            obj.close();

        } catch (SQLException sqlEx) {
            System.out.println("SQL Error: " + sqlEx.getMessage());
            sqlEx.printStackTrace();  // This will provide more details about the SQL exception.
        } catch (ClassNotFoundException classEx) {
            System.out.println("Driver Error: " + classEx.getMessage());
            classEx.printStackTrace();  // This will provide details if the JDBC driver is missing.
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
            e.printStackTrace();  // This will print the full stack trace of any other errors.
        }
    }
}
