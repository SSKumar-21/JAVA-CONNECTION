import java.sql.*;
import java.util.Scanner;
import java.lang.*;

class UserInput {
     public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);

        // Load Oracle JDBC Driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Establish connection
        Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:XE", "system", "system");
	for(int i=0;i<5;i++){
        // User input
        System.out.print("Enter EMP ID (int): ");
        int id = sc.nextInt();

        System.out.print("Enter EMP Name: ");
        String name = sc.nextLine();

        System.out.print("Enter EMP Salary: ");
        int sal = sc.nextInt();

	System.out.print("Enter EMP DESG: ");
        String desg = sc.nextLine();

        // Use PreparedStatement for inserting data
        String insertSQL = "INSERT INTO EMP (ID, NAME, SALARY,DESG) VALUES ("+id+","+name+","+sal+","+desg+")";
        PreparedStatement pst = con.prepareStatement(insertSQL);
	
	pst.setInt(1, id);
        pst.setString(2, name);
        pst.setString(3, branch);
    
        int k = pst.executeUpdate();

        if (k > 0)
            System.out.println("Record inserted successfully.");
        else
            System.out.println("Insertion failed. Try again.");
}
        // Display all students
        String selectSQL = "SELECT * FROM STUDENT";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(selectSQL);

        System.out.println("\n--- STUDENT TABLE ---");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + "\t" + rs.getString("NAME") + "\t" +rs.getString("BRANCH"));
        }

        // Close resources
        rs.close();
        st.close();
        pst.close();
        con.close();
    }
}
