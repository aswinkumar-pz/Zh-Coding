import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;	


public class ViewAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private DBJob db = new DBJob();
	private HashMap<Integer,User>  users = db.executeQueryForFetch("select * from employee");
	
	private int []a = {1,2,3};
	
	public int[] getA() {
		
		return a;
	}

	public void setA(int[] a) {
		this.a = a;
	}

	public HashMap<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}
	
	

	public String execute() {	
		users = db.executeQueryForFetch("select * from employee");
		System.out.print(SUCCESS);
		return SUCCESS;
	}
}
