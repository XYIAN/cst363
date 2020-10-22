import java.sql.*;
import java.util.Scanner;

public class JdbcDemo {

	public static void main(String[] args) {
		try {
			Scanner keyboard = new Scanner(System.in);
			
			// get connection and setAutoCommit false which means
			// to use transactions
			Connection conn = DriverManager.getConnection(
					"jdbc:h2:file:c:/h2/bin/data/exam", 
					"sa", // user
					""); // password
			conn.setAutoCommit(false); // use transactions, commit & rollback

			// get user input
			boolean color = false;
			String type;
			System.out.print(" Color printer?  yes or no ");
			if (keyboard.nextLine().startsWith("y")) {
				color=true;
			}
			System.out.print(" Printer type:  ink-jet, laser, ? ");
			type = keyboard.nextLine();

			// prepare sql
			String sql = "select maker, product.model, price, color, printer.type "
					+ "from product join printer on product.model=printer.model "
					+ "where  printer.color = ? and printer.type=?";
			String usql = "update printer set price = ? where model=?";

			 

			// set the sql parameters 
			 
			
			// execute the query and process the result set
			 

			while (       ) {
				// process columns for one row
				 

				System.out.printf("%s\t %s\t  $%.2f\t %b\t %s\n", maker, model, price, pcolor, ptype);

				// update price by $50.00
				 
				if (        ) {
					System.out.println("ERROR. update failed. " + model);
					conn.rollback(); // abort any updates and terminate
					conn.close();
					System.exit(0);
				}

			}

			// successful end of program. 
			// If no commit, then updates are not saved.
			conn.commit();
			conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// since no commit is done. 
			// Any updates will not be saved.
		}
	}

}