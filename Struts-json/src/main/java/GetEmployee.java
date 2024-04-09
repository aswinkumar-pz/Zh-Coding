import com.opensymphony.xwork2.ActionSupport;

public class GetEmployee extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private Employee employee;
	private String id;
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String execute() {
		EmployeeDAO empdao = new EmployeeDAO();
		employee = empdao.getEmployeeById(Integer.parseInt(id));
		return SUCCESS;
	}
	
	
}