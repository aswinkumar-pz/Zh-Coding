import java.util.HashMap;

public class EmployeeDAO {
	
	private DBJob db = new DBJob();;
	
	public void addEmployee(Employee emp) {
		
		String name = emp.getName();
		int manager_id = emp.getManager_id();
		db.executeUpdateQuery("insert into employee(name,manager_id) value('"+name+"',"+manager_id+")");
	}
	
	public HashMap<Integer,Employee> getAllEmployee() {
		
		HashMap<Integer,Employee> employees = db.executeQueryForFetch("select * from employee");
		return employees;
	}
	
	public Employee getEmployeeById(int id) {
		
		Employee employee = (db.executeQueryForFetch("select * from employee where id="+id)).get(id);
		return employee;
	}
	
	public void editEmployee(Employee emp) {
		
		int id = emp.getId();
		String name = emp.getName();
		int manager_id = emp.getManager_id();
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
