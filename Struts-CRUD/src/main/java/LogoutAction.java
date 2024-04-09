import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	
	
	private Map<String,Object> session;
	
	public String execute() {
		
		message = "Logged Out!!!";
		session.clear();
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
