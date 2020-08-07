package api;
import java.sql.*;
import java.util.Scanner;

public class JdbcSelectTest {
	public static void main(String[] args) {
		String portNumber = "3306";
		String tableName = "ebookshop";
		Scanner input = new Scanner(System.in);
		String username = "";
		String password = "";
		System.out.println("Database connection procedure. Enter your db username: ");
		username = input.nextLine();
		System.out.println("Enter your db password");
		password = input.nextLine();
		try (
			// Attempt to connect using the jdbc driver with the credentials supplied.
			// This assumes a connection to localhost and the default port of 3306,
			// as well as points to a specific table that I set up for this test run.
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:" + portNumber + "/" + tableName +
					"?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
					username, password);
			Statement stmt = conn.createStatement();
		) {
	        if (stmt != null) {
	        	System.out.println("Successfully connected!\n");
	        	String strSelect = "select title, price, qty from books";
		        ResultSet rset = stmt.executeQuery(strSelect);
		        
		        System.out.println("Records selected:");
		        int rowCount = 0;
		        while (rset.next()) {
		        	String title = rset.getString("title");
		        	double price = rset.getDouble("price");
		        	int qty = rset.getInt("qty");
		        	System.out.println(title + ", " + price + ", " + qty);
		        	++rowCount;
		        }
	        System.out.println("Total number of records: " + rowCount);
	        }
	        
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		 finally { input.close(); }
	}
}
