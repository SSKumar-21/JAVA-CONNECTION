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

        // Prepare SQL statement with parameters
        String insertSQL = "INSERT INTO EMP (ID, NAME, SALARY, DESG) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(insertSQL);

        // Loop to take 5 employee records
        for (int i = 1; i <= 5; i++) {
            System.out.println("\nEnter details for Employee " + i + ":");

            System.out.print("Enter EMP ID (int): ");
            int id = sc.nextInt();
            sc.nextLine(); // clear newline

            System.out.print("Enter EMP Name: ");
            String name = sc.nextLine();

            System.out.print("Enter EMP Salary (int): ");
            int sal = sc.nextInt();
            sc.nextLine(); // clear newline

            System.out.print("Enter EMP Designation: ");
            String desg = sc.nextLine();

            // Set values in PreparedStatement
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setInt(3, sal);
            pst.setString(4, desg);

            // Execute insert
            int k = pst.executeUpdate();

            if (k > 0)
                System.out.println("Record inserted successfully.");
            else
                System.out.println("Insertion failed. Try again.");
        }

        // Display all employees
        String selectSQL = "SELECT * FROM EMP";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(selectSQL);

        System.out.println("\n--- EMP TABLE ---");
        System.out.println("ID\tNAME\tSALARY\tDESG");
        System.out.println("------------------------------------");
        while (rs.next()) {
            System.out.println(
                rs.getInt("ID") + "\t" +
                rs.getString("NAME") + "\t" +
                rs.getInt("SALARY") + "\t" +
                rs.getString("DESG")
            );
        }

        // Close resources
        rs.close();
        st.close();
        pst.close();
        con.close();
        sc.close();
    }
}