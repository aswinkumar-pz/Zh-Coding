import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditAction {

	private UserDAO udao = new UserDAO();
	private HashMap<Integer, User> users;
	private String name;
	private int id;
	private int manager_id;
	private User user = new User();
	private String message;

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public HashMap<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String execute() {

		users = udao.getAllEmployee();
		user = users.get(id);
		name = user.getName();
		manager_id = user.getManager_id();
		
		

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
			if (levels.get(i).contains(manager_id)) {

				break;
			}
			for (int j : levels.get(i)) {
				users.remove(j);
			}
		}

		return "success";
	}

	public String finish() {
		
		if(name.strip().isEmpty()) {
			message = "Your name field should not be empty!!";
			return "success";
		}
		
		user = new User();
		user.setId(id);
		user.setName(name);
		user.setManager_id(manager_id);
		udao.editEmployee(user);
		message = "Employeee updated successfully";
		return "success";
	}

}
