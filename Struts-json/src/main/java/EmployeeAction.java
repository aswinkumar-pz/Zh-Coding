import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;

public class EmployeeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<Employee> employees = null;
	private Employee employee = null;
	private String id="";
	private String name="";
	private String manager_id="";
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	
	public String displayAll() {
		
		employees = new ArrayList<>(employeeDAO.getAllEmployee().values());
		return SUCCESS;
	}
	
	public String addEmployee() {

		employeeDAO = new EmployeeDAO();
		employee = new Employee();
		employee.setName(name);
		employee.setManager_id(Integer.parseInt(this.manager_id));
		employeeDAO.addEmployee(employee);	
		return SUCCESS;
	}
	
	public String getEmployeeById() {
		
		int id = Integer.parseInt(this.id);
		employee = employeeDAO.getEmployeeById(id);
		return SUCCESS;
	}

	public String getAllSeniors() {
		
		HashMap<Integer,Employee> employees = employeeDAO.getAllEmployee();
		int id = Integer.parseInt(this.id);
		this.employee = employees.get(id);
		int manager_id = employee.getManager_id();
		
		List<List<Integer>> levels = new ArrayList<>();
		List<Integer> currentLevel = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		currentLevel.add(1);
		levels.add(currentLevel);
		while (!currentLevel.isEmpty()) {
			for (int i : currentLevel) {
				for (Map.Entry<Integer, Employee> emp : employees.entrySet()) {
					if (emp.getValue().getManager_id() == i) {
						temp.add(emp.getKey());
					}
				}
			}
			currentLevel = temp;
			temp = new ArrayList<Integer>();
			levels.add(currentLevel);
		}

		for (int i = levels.size() - 1; i >= 0; i--) {
			if (levels.get(i).contains(manager_id)) {
				break;
			}
			for (int j : levels.get(i)) {
				employees.remove(j);
			}
		}
		
		this.employees = new ArrayList<Employee>(employees.values());
		
		return SUCCESS;
	}
}
