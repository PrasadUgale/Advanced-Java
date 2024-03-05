import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	private static Properties properties = null;

	//Prevents instantiation of JdbcUtil Class
	private JdbcUtil() {
	}

	//Executed only once when the class is loaded
	static {
		FileInputStream fis = null;

		//path to properties file where db realated data is specified [Modify as Required]
		String fileInfo = ".\\jdbc.properties";

		//loading the properties from jdbc.properties file
		try {
			fis = new FileInputStream(fileInfo);
			if (fis != null) {
				properties = new Properties();
				properties.load(fis);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//provide Connection object using driver manager and properties specified by user in jdbc.properties file
	public static Connection getDBConection() throws IOException, SQLException {
		return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),properties.getProperty("password"));
	}
	
	//close all the resourses used by util class to avoid resource leak
	public static void cleanUpResources(ResultSet resultSet, Statement statement, Connection connection) {
		
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
