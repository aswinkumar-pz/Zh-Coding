import java.util.HashMap;
import java.util.Map;

public class ViewAction {
	
	private DBJob db = new DBJob();
	private HashMap<Integer,User> usersHm = db.executeQueryForFetch("select * from employee");
	private User []users;
	
	public User [] getUsers() {
		users = new User[usersHm.size()];
		int i=0;
		for(Map.Entry<Integer, User> user : usersHm.entrySet()) {
			users[i] = user.getValue();
		}
		return users;
	}
	
	public String execute() {
		return "success";
	}
	
	
}
