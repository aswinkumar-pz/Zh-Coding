public class UserAction {
	int id;
	String name;
	
	public String execute(){
		this.id = id;
		this.name = name;
	    return "success";  
	}
}
