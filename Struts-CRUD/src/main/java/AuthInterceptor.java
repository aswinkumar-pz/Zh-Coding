import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class AuthInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (!isAuthenticated()) {
            return "login";
        }
        return invocation.invoke();
    }

    private boolean isAuthenticated() {
        return getSession().containsKey("loggedIn") && (boolean) getSession().get("loggedIn");
    }
    
    private Map<String, Object> getSession() {
        return ActionContext.getContext().getSession();
    }
}
