import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DBJob {
	
	private String username = "root";
	private String password = "akpassword";
	private String url =  "jdbc:mysql://localhost:3306/employees";
	private Connection connect = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private HashMap<Integer,User> hm = null;
	
	DBJob() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connect = DriverManager.getConnection(url,username,password);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	HashMap<Integer,User> executeQueryForFetch(String query) {
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hm;
	}
	
	void executeUpdateQuery(String query) {
		try {
			ps = connect.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void closeConnections() {
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
	
	
}
