import java.sql.*;
import java.util.Scanner;

class UserInput {
     public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);

        // Load Oracle JDBC Driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Establish connection
        Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:XE", "system", "system");

        // User input
        System.out.print("Enter Student ID (int): ");
        int id = sc.nextInt();

        sc.nextLine(); // consume newline

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Branch: ");
        String branch = sc.nextLine();

        // Use PreparedStatement for inserting data
        String insertSQL = "INSERT INTO STUDENT (ID, NAME, BRANCH) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(insertSQL);

        // Set parameters safely
        pst.setInt(1, id);
        pst.setString(2, name);
        pst.setString(3, branch);

        // Execute the update
        int k = pst.executeUpdate();

        if (k > 0)
            System.out.println("Record inserted successfully.");
        else
            System.out.println("Insertion failed. Try again.");

        // Display all students
        String selectSQL = "SELECT * FROM STUDENT";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(selectSQL);

        System.out.println("\n--- STUDENT TABLE ---");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + "\t" +
                               rs.getString("NAME") + "\t" +
                               rs.getString("BRANCH"));
        }

        // Close resources
        rs.close();
        st.close();
        pst.close();
        con.close();
    }
}
