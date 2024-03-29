import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class SampleMain{

	public static void main(String[] args) {
		try {
			//Get connection from utility class
			Connection con = JdbcUtil.getDBConection();
			
			//Prepare a query to execute
			Statement stmt = con.createStatement();
			String query ="Select * from user;";
			
			//Execute the query and display result
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getString(1)+"//"+rs.getString(2)+"//"+rs.getString(3)+"//"+rs.getString(4)+"//");
			}
			
		} catch (IOException | SQLException e) {
			System.out.println("Unable to establish connection");
			e.printStackTrace();
		}

}
