import com.opensymphony.xwork2.ActionSupport;

public class AddAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private String name="";
	private int manager_id;
	private DBJob db = new DBJob();
	private String message = "";
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	
	public String input() {
		return "input";
	}
	
	@Override
	public String execute() {
		try {
			if(name=="") {
				 throw new Exception("Name field is empty");
			}
			db.executeUpdateQuery("insert into employee(name,manager_id) values('"+this.name+"',"+this.manager_id+")");
			message = "Employee added successfully";
		}
		catch(Exception e)
		{
			message = e.getMessage() + "! Please try again";
		}
		return SUCCESS;
	}
}
