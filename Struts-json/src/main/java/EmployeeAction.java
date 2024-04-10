import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.opensymphony.xwork2.ActionSupport;

public class EmployeeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<Employee> employees = null;
	private Employee employee = null;
	private String id="";
	private String name="";
	private String manager_id="";
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	private String message = "";
	
	
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
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/* Display all employee */
	public String displayAll() {
		
		employees = new ArrayList<>(new TreeMap<>(employeeDAO.getAllEmployee()).values());
		return SUCCESS;
	}
	
	/* To add new employee */
	public String addEmployee() {

		if(name.isEmpty()) {
			message = "Name is empty";
			return ERROR;
		}
		else if(manager_id.isEmpty()) {
			message = "Manager_id is empty";
			return ERROR;
		}
		else if(employeeDAO.getEmployeeById(Integer.parseInt(manager_id))==null) {
			message = "No manager is found for the given id";
			return ERROR;
		}
		else {
			employeeDAO = new EmployeeDAO();
			employee = new Employee();
			employee.setName(name);
			employee.setManager_id(Integer.parseInt(this.manager_id));
			employeeDAO.addEmployee(employee);
			message = "Employee "+ name +" added successfully";
		}
		return SUCCESS;
	}
	
	/* To get details of an employee by ID */
	public String getEmployeeById() {
		
		int id = Integer.parseInt(this.id);
		employee = employeeDAO.getEmployeeById(id);
		if(employee == null) {
			message = "No employee found";
			return ERROR;
		}
		else {
			message = employee.toString();
			message = message+"-"+employeeDAO.getEmployeeById(employee.getManager_id()).getName();
			return SUCCESS;
		}
		
	}

	/* To get all managers and seniors of the employee */
	public String getAllSeniors() {
		
		if(employeeDAO.getEmployeeById(Integer.parseInt(id))==null) {
			message = "No employee is found";
			return ERROR;
		}
		
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
		
		this.employees = new ArrayList<Employee>(new TreeMap<>(employees).values());
		
		return SUCCESS;
	}
	
	/* To update the details of the employee */
	public String updateEmployee() {
		
		if(name.isEmpty()) {
			message = "Name field is empty";
			return ERROR;
		}
		if(manager_id.isEmpty()) {
			message = "Manager id is empty";
			return ERROR;
		}
		if(employeeDAO.getEmployeeById(Integer.parseInt(id))==null) {
			message = "No user found for the given id";
			return ERROR;
		}
		if(employeeDAO.getEmployeeById(Integer.parseInt(manager_id))==null) {
			message = "No manager found for the given id";
			return ERROR;
		}
		
		employee = new Employee();
		employee.setId(Integer.parseInt(id));
		employee.setName(name);
		employee.setManager_id(Integer.parseInt(manager_id));
		employeeDAO.editEmployee(employee);
		String managerName = employeeDAO.getEmployeeById(Integer.parseInt(manager_id)).getName();
		message = "Employee updated successfully"
				+ "\n Name:- "+name
				+ "\n Employee id:-"+id
				+ "\n Manager name:-"+managerName;
		
		return SUCCESS;
	}
	
	/* To get seniors except the one */
	public String getAllSeniorsExceptOne() {
		
		if(id.isEmpty() || employeeDAO.getEmployeeById(Integer.parseInt(id))==null) {
			message = "Invalid id";
			return ERROR;
		}
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
				employees.remove(id);
				break;
			}
			for (int j : levels.get(i)) {
				employees.remove(j);
			}
		}
		
		this.employees = new ArrayList<Employee>(new TreeMap<>(employees).values());
		
		return SUCCESS;
	}

	/* To update manager id of juniors before deletion */
	public String updateJuniorsManagerId() {
		
		if(id.isEmpty() || employeeDAO.getEmployeeById(Integer.parseInt(id))==null) {
			message = "Invalid employee id";
			return ERROR;
		}
		if(manager_id.isEmpty() || employeeDAO.getEmployeeById(Integer.parseInt(manager_id))==null) {
			message = "Invalid manager id";
			return ERROR;
		}
		
		int id = Integer.parseInt(this.id);
		int manager_id = Integer.parseInt(this.manager_id);
		employeeDAO.updateManagerId(id, manager_id);
		message = "Manager details updated successfully";
		return SUCCESS;
	}
	
	/* Delete employee details */
	public String deleteEmployee() {
		
		int id = Integer.parseInt(this.id);
		if(employeeDAO.getEmployeeById(id)==null) {
			message = "Employee not found";
			return ERROR;
		}
		employeeDAO.deleteEmployeeById(id);
		message = "Employee deleted successfully";
		return SUCCESS;
	}

}