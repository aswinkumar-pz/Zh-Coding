import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class DetailsAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private DBJob db = new DBJob();
	private HashMap<Integer,User> users;
	private List<Integer> ids;
	private List<String> idName;
	
	public List<String> getIdName() {
		return idName;
	}

	public void setIdName(List<String> idName) {
		this.idName = idName;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public HashMap<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}

	public String execute() {
		users = db.executeQueryForFetch("select * from employee");
		ids = new ArrayList<Integer>();
		idName = new ArrayList<String>();
		for(Map.Entry<Integer, User> user : users.entrySet()) {
			ids.add(user.getValue().id);
			idName.add(user.getValue().toString());
		}
		
		return SUCCESS;
	}
	
	

}
