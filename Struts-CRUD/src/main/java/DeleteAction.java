import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private UserDAO udao = new UserDAO();
	private HashMap<Integer, User> users;
	private int id;
	private User user = new User();
	private String message;
	private int new_manager_id;

	public HashMap<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNew_manager_id() {
		return new_manager_id;
	}

	public void setNew_manager_id(int new_manager_id) {
		this.new_manager_id = new_manager_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String execute() {

		users = udao.getAllEmployee();
		user = users.get(id);

		List<List<Integer>> levels = new ArrayList<>(); // Assigning levels so that they can easily placed in the table
		List<Integer> currentLevel = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		currentLevel.add(1);
		levels.add(currentLevel);
		while (!currentLevel.isEmpty()) {
			for (int i : currentLevel) {
				for (Map.Entry<Integer, User> us : users.entrySet()) {
					if (us.getValue().getManager_id() == i) {
						temp.add(us.getKey());
					}
				}
			}
			currentLevel = temp;
			temp = new ArrayList<Integer>();
			levels.add(currentLevel);
		}

		for (int i = levels.size() - 1; i >= 0; i--) {
			if (levels.get(i).contains(id)) {
				users.remove(id);
				break;
			}
			for (int j : levels.get(i)) {
				users.remove(j);
			}
		}

		return "success";
	}

	public String finish() {
		udao.updateManagerId(id,new_manager_id);
		udao.deleteEmployeeById(id);
		message = "Employeee deleted successfully";
		return "success";
	}
}
