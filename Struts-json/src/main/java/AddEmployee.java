import com.opensymphony.xwork2.ActionSupport;

public class AddEmployee extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	private EmployeeDAO empdao;
	private transient String name;
	private transient String manager_id;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setName(String name) {
        this.name = name;
    }
	
    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

	public String execute() {
		
		employee = new Employee();
		employee.setName(name);
		employee.setManager_id(Integer.parseInt(manager_id));
		empdao = new EmployeeDAO();
		empdao.addEmployee(employee);
		return SUCCESS;	
	}
}
