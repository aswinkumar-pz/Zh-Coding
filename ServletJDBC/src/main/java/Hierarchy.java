

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hierarchy extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>View Hierarchy</title></head><body>");
		out.println("<h3>Employee's hierarchy</h3>");
		
		
		
		
		
		
		
		
		out.println("<form action='home.html'><button type='submit'>Click here to home page</button></form>");
		out.println("</body></html>");
	
	}
}
