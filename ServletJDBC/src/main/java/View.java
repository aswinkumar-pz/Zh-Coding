

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><head>");
		out.print("<title> View details </title>");
		out.print("</head><body><table>");
		out.print("<tr> <th>Id</th> <th>Name</th> <th>Manager-id</th> </tr>");
		try {
			List<User> users = EmpDao.getAllEmployee();
			for(int i=0;i<users.size();i++) {
				out.println("<tr>");
				out.println("<td>");out.println(users.get(i).id);out.println("</td>");
				out.println("<td>");out.println(users.get(i).name);out.println("</td>");
				out.println("<td>");out.println(users.get(i).manager_id);out.println("</td>");
				out.println("</tr>");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		out.print("</table>");
		out.println("<form action='home'><button type='submit'>Click here to home page</button></form>");
		out.println("<form action='logout'><button type='submit'>Click here to logout</button></form>");
		out.print("</body>");
		out.print("</html>");
	}
}
