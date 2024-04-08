import java.util.HashMap;

public class UserDAO {
	
	private DBJob db = new DBJob();;
	
	public void addEmployee(User us) {
		
		String name = us.getName();
		int manager_id = us.getManager_id();
		db.executeUpdateQuery("insert into employee(name,manager_id) value('"+name+"',"+manager_id+")");
	}
	
	public HashMap<Integer,User> getAllEmployee() {
		
		HashMap<Integer,User> employees = db.executeQueryForFetch("select * from employee");
		return employees;
	}
	
	public User getEmployeeById(int id) {
		
		User user = (db.executeQueryForFetch("select * from user where id="+id)).get(id);
		return user;
	}
	
	public void editEmployee(User user) {
		
		int id = user.getId();
		String name = user.getName();
		int manager_id = user.getManager_id();
		db.executeUpdateQuery("update employee set name='"+name+"',manager_id="+manager_id+" where id="+id);
	}
	
	public void deleteEmployeeById(int id) {
		db.executeUpdateQuery("delete from employee where id="+id);
	}
	
	public void updateManagerId(int old_manager_id,int new_manager_id) {
		db.executeUpdateQuery("update employee set manager_id="+new_manager_id+" where manager_id="+old_manager_id);
		System.out.print(new_manager_id);
		System.out.print(" "+old_manager_id);
	}
	
}
