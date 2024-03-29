

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		out.print("<html><head>");
		out.print("<title>Home page</title></head><body>");
		out.print("<form action='add'><label>To enter an employee detail</label>");
		out.print("<button type='submit'>Click here</button></form>");
		out.print("<form action='view'><label>To view all employee details</label>");
		out.print("<button type='submit'>Click here</button></form>");
		out.print("<form action='edit'><label>To edit an employee detail</label>");
		out.print("<button type='submit'>Click here</button></form>");
		out.print("<form action='delete'><label>To delete an employee detail</label>");
		out.print("<button type='submit'>Click here</button></form>");
		out.print("<form action='level'><label>TTo view hierarchy and levels</label>");
		out.print("<button type='submit'>Click here</button></form>");
		out.print("</form></body></html>");
		
	}

}
