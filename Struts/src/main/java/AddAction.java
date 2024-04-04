import java.util.*;

public class AddAction {

	private String name;
	private int manager_id;
	private DBJob db = new DBJob();
	private HashMap<Integer,User> users = db.executeQueryForFetch("select * from employee");
	private List<Integer> ids = new ArrayList<>(users.keySet());
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
	
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
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
	
	public String execute() {
		db.executeUpdateQuery("insert into employee(name,manager_id) values('"+this.name+"',"+this.manager_id+")");
		message = "Employee added successfully";
		return "success";
	}
}
