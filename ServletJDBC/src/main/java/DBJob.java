import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class DBJob {
	
	private String username = "root";
	private String password = "akpassword";
	private String url =  "jdbc:mysql://localhost:3306/employees";
	private Connection connect = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private HashMap<Integer,User> hm = null;
	
	private void closeConnections() {
		try {
			rs.close();
		}catch (Exception e) {
			;
		}
		try {
			ps.close();
		}catch (Exception e) {
			;
		}
		try {
			connect.close();
		}catch (Exception e) {
			;
		}
	}
	
	HashMap<Integer,User> executeQueryForFetch(String query) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url,username,password);
			ps = connect.prepareStatement(query);
			rs = ps.executeQuery();
			User user = new User();
			hm = new HashMap<>();
			while(rs.next()) {
				user.id = rs.getInt(1);
				user.name = rs.getString(2);
				user.manager_id = rs.getInt(3);
				hm.put(user.id,user);
				user = new User();
			}
			closeConnections();
		} catch (Exception e) {
			e.printStackTrace();
			closeConnections();
		}
		return hm;
	}
	
	void executeUpdateQuery(String query) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url,username,password);
			ps = connect.prepareStatement(query);
			ps.executeUpdate();
			closeConnections();
		} catch (Exception e) {
			e.printStackTrace();
			closeConnections();
		}
	}
}
