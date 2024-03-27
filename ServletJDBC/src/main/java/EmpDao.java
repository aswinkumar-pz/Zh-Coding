import java.sql.*;
import java.util.*;


public class EmpDao {
	
	public static Connection dbConnect() {
		Connection connect = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(DBUtils.url,DBUtils.username,DBUtils.password);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return connect;
	}
	
	public static List<Integer> getAllEmployeeId() throws SQLException {
		List<Integer> empIds = new ArrayList<>();
		Connection connect = dbConnect();
		Statement sm = connect.createStatement();
		ResultSet rs = sm.executeQuery("select id from employee");
		while(rs.next()) {
			empIds.add(rs.getInt(1));
		}
		connect.close();
		return empIds;
	}
	
	public static void addEmployee(String name,int manager_id) throws SQLException {
		Connection connect = dbConnect();
		PreparedStatement ps = connect.prepareStatement("insert into employee(name,manager_id) values(?,?);");
		ps.setString(1,name);
		ps.setInt(2, manager_id);
		ps.executeUpdate();
		connect.close();
	}
	
	public static User getEmployeeDetailsById(int id) throws SQLException {
		
		User user = new User();
		Connection connect = dbConnect();
		PreparedStatement ps = connect.prepareStatement("select * from employee where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			user.id = rs.getInt(1);
			user.name = rs.getString(2);
			user.manager_id = rs.getInt(3);
		}
		return user;
		
	}
	
	public static void editEmployeeDetails(User user) throws SQLException {
		
		Connection connect = dbConnect();
		PreparedStatement ps = connect.prepareStatement("update employee set name=?,manager_id=? where id=?");
		ps.setString(1,user.name);
		ps.setInt(2, user.manager_id);
		ps.setInt(3, user.id);
		ps.executeUpdate();
		connect.close();
		
	}
	
	public static void deleteEmployee(int id) throws SQLException {
		
		Connection connect = dbConnect();
		
		PreparedStatement ps = connect.prepareStatement("select manager_id from employee where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		int sm_id=0;
		while(rs.next()) {
			sm_id = rs.getInt(1);
		}

		ps = connect.prepareStatement("update employee set manager_id=? where manager_id=?");
		ps.setInt(1, sm_id);
		ps.setInt(2,id);
		ps.executeUpdate();
		
		ps = connect.prepareStatement("delete from employee where id=?");
		ps.setInt(1, id);
		ps.executeUpdate();
		
		connect.close();
	}
	
	public static List<User> getAllEmployee() throws SQLException {
		
		Connection connect = dbConnect();
		List<User> users = new ArrayList<>();
		Statement sm = connect.createStatement();
		ResultSet rs = sm.executeQuery("select * from employee order by manager_id");
		
		while(rs.next()) {
			User user = new User();
			user.id = rs.getInt(1);
			user.name = rs.getString(2);
			user.manager_id = rs.getInt(3);
			users.add(user);
		}
		
		connect.close();
		return users;
	}
	
	public static List<User> getAllEmployeeExceptAdmin() throws SQLException {
		
		Connection connect = dbConnect();
		List<User> users = new ArrayList<>();
		Statement sm = connect.createStatement();
		ResultSet rs = sm.executeQuery("select * from employee where manager_id is not null order by manager_id");
		
		while(rs.next()) {
			User user = new User();
			user.id = rs.getInt(1);
			user.name = rs.getString(2);
			user.manager_id = rs.getInt(3);
			users.add(user);
		}
		
		connect.close();
		return users;
	}
	
	public static HashMap<Integer,User> getAllEmployeeExceptJunior(int id) throws SQLException {
		
		List<User> users = getAllEmployee();
		Queue<Integer> empIds = new LinkedList<>();
		users.remove(getEmployeeDetailsById(id));
		HashMap<Integer,User> seniors = new HashMap<>();
		for(User user : users) {
			seniors.put(user.id,user);
		}
		empIds.add(id);
		while(!empIds.isEmpty()) {
			for(User user : users) {
				if(user.manager_id == empIds.peek()) {
					empIds.add(user.id);
					seniors.remove(user.id);
				}
			}
			empIds.poll();
		}
		return seniors;
	} 
}
