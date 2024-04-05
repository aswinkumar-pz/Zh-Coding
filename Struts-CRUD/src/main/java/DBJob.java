import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class DBJob {
	
	private String username = "root";
	private String password = "akpassword";
	private String url =  "jdbc:mysql://localhost:3306/employees";
	
	
	private void closeConnections(Connection connect,PreparedStatement ps,ResultSet rs) {
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
		HashMap<Integer,User> hm = new HashMap<>();
		Connection connect = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url,username,password);
			ps = connect.prepareStatement(query);
			rs = ps.executeQuery();
			User user = new User();
			while(rs.next()) {
				user.id = rs.getInt(1);
				user.name = rs.getString(2);
				user.manager_id = rs.getInt(3);
				hm.put(user.id,user);
				user = new User();
			}
			closeConnections(connect,ps,rs);
		} catch (Exception e) {
			e.printStackTrace();
			closeConnections(connect,ps,rs);
		}
		return hm;
	}
	
	void executeUpdateQuery(String query) {
		Connection connect = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url,username,password);
			ps = connect.prepareStatement(query);
			ps.executeUpdate();
			closeConnections(connect,ps,rs);
		} catch (Exception e) {
			e.printStackTrace();
			closeConnections(connect,ps,rs);
		}
	}
}
