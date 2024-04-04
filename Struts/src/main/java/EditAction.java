import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class EditAction {
	
	private DBJob db = new DBJob();
	private HashMap<Integer,User> users;
	private List<String> nonJuniors;
	private String name;
	private int id;
	private String user;
	private int manager_id;
	
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
	
	public List<String> getNonJuniors() {
		return nonJuniors;
	}
	
	public void setNonJuniors(List<String> nonJuniors) {
		this.nonJuniors = nonJuniors;
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
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String execute() {
		
		String[] parts = user.split("-");
        setId(Integer.parseInt(parts[0]));
        users = db.executeQueryForFetch("select * from employee");
        
        nonJuniors = new ArrayList<>();
        
        Queue<Integer> queue = new LinkedList<>();
		queue.add(id);
		users.remove(id);
		while (!queue.isEmpty()) {
		    int currentId = queue.poll();
		    Iterator<Map.Entry<Integer, User>> iterator = users.entrySet().iterator();
		    while (iterator.hasNext()) {
		        Map.Entry<Integer, User> i = iterator.next();
		        if (i.getValue().manager_id == currentId) {
		            queue.add(i.getKey());
		            iterator.remove();
		        }
		    }
		}
		for(Map.Entry<Integer, User> us : users.entrySet()) {
			nonJuniors.add(us.getValue().toString());
		}
		
        return "success";
	}
	
	public String finish() {
		
		
		return "success";
	}

}
