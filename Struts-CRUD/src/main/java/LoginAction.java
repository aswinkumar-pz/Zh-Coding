import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class LoginAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private Map<String, Object> session;
	private String message;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String execute() {
		if(username.equals("admin") && password.equals("pass")) {
			session.put("loggedIn", true);
			return SUCCESS;
		}
		else {
			message = "Invalid credentials!!!";
			return ERROR;
		}
		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
