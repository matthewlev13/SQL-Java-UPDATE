import java.sql.*;

public class JdbcSelectTest {
    public static void main(String[] args) {
        try {
            // Step 1: allocate a database 'Connection' object
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "matthewlevis", "xxxxxxx");
            // Step 2: allocate a 'statement' object in the connection
            Statement stmt = conn.createStatement();

            // Step 3 & 4: Execute a SQL UPDATE via executeUpdate()
            // which returns an int indicating the number of rows affected.
            // increase the price by 7% and qty by 1 for id=1001
            String strUpdate = "update books set price = price * 0.7, qty = qty + 1 where id = 1001";
            System.out.println("The SQL query is: " + strUpdate);
            int countUpdated = stmt.executeUpdate(strUpdate);
            System.out.println(countUpdated + " records affected.");

            //ResultSet rset = stmt.executeQuery(strSelect);

            // Step 3 & 4: Issue a SELECT to check the UPDATE
            String strSelect = "select * from books where id = 1001";
            System.out.println("The SQL query is: " + strSelect); 
            ResultSet rset = stmt.executeQuery(strSelect);
            while(rset.next()) {
                String title = rset.getString("title");
                double price = rset.getDouble("price");
                int qty = rset.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        // Step 5: close the resources - done automatically by try-with resources statement
    }
}