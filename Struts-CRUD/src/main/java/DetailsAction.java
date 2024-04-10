import java.util.TreeMap;

import com.opensymphony.xwork2.ActionSupport;

public class DetailsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private UserDAO udao = new UserDAO();
	private TreeMap<Integer, User> users;

	public TreeMap<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(TreeMap<Integer, User> users) {
		this.users = users;
	}

	public String execute() {
		users = new TreeMap<>(udao.getAllEmployee());
		return SUCCESS;
	}

	public String edit() {
		users = new TreeMap<>(udao.getAllEmployee());
		users.remove(1);	
		return SUCCESS;
	}

}
