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

	private JdbcUtil() {
	}

	static {
		FileInputStream fis = null;

		String fileInfo = ".\\jdbc.properties";
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

	public static Connection getDBConection() throws IOException, SQLException {
		
		return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
				properties.getProperty("password"));
	}

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
