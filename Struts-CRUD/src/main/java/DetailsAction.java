import java.util.TreeMap;

import com.opensymphony.xwork2.ActionSupport;

public class DetailsAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private DBJob db = new DBJob();
	private TreeMap<Integer,User> users;
	

	public TreeMap<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(TreeMap<Integer, User> users) {
		this.users = users;
	}

	public String execute() {
		users = new TreeMap<>(db.executeQueryForFetch("select * from employee"));		
		return SUCCESS;
	}
	public String edit() {
		users = new TreeMap<>(db.executeQueryForFetch("select * from employee where id!=1"));
		return SUCCESS;
	}

}
