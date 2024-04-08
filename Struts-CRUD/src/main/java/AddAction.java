import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AddAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;

	private User user = new User();
	private UserDAO udao = new UserDAO();
	private String message = "";
	
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

	@Override
	public String execute() {
		try {
			if (user.getName() == "") {
				throw new Exception("Name field is empty");
			}
			udao.addEmployee(user);
			message = "Employee added successfully";
		} catch (Exception e) {
			message = e.getMessage() + "! Please try again";
		}
		return SUCCESS;
	}
	
	public User getModel() {
		
		return user;
	}
}
