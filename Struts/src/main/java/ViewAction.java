import java.util.HashMap;
import java.util.Map;
import java.util.*;
import com.opensymphony.xwork2.ActionSupport;	


public class ViewAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private DBJob db = new DBJob();
	private HashMap<Integer,User> usersHm = db.executeQueryForFetch("select * from employee");
	private ArrayList<User> users = new ArrayList<>();
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public String execute() {	
		for(Map.Entry<Integer, User> user : usersHm.entrySet()) {
			User us = new User();
			us = user.getValue();
			users.add(us);
		}
		System.out.print(SUCCESS);
		return SUCCESS;
	}
	
	public String input() {
		
		System.out.print(INPUT);
		return INPUT;
	}
}
