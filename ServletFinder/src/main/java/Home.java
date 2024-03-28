
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Home extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("username");
		out.print("<html><head><title>Home page</title></head><body>");
		out.print("<h1> Hello "+name+"!!</h1><br>");
		out.print("<form action='logout'><button type=submit>Logout</button></form>");
		out.print("</body></html>");
	}

}
