

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
		out.print("<tr style='text-align: left;'>  <th>Name</th> <th>&nbsp&nbsp</th><th>Manager-id</th> </tr>");
		DBJob db = new DBJob();
		HashMap<Integer,User> u = db.executeQueryForFetch("select * from employee order by id");
		TreeMap<Integer,User> users = new TreeMap<>(u);
		for(Map.Entry<Integer,User> user : users.entrySet()) {
			out.println("<tr>");
			out.println("<td>");out.println(user.getValue().name+"("+user.getKey()+")");out.println("</td>");
			out.println("<td>&nbsp&nbsp</td>");
			out.println("<td>");
			try {
				out.println(users.get(user.getValue().manager_id).name + "-");out.println(user.getValue().manager_id);
			} catch(Exception e) {
				out.println("None");
			}
			out.println("</td>");
			out.println("</tr>");
		}
		out.print("</table>");
		out.println("<form action='home'><button type='submit'>Click here to home page</button></form>");
		out.println("<form action='logout'><button type='submit'>Click here to logout</button></form>");
		out.print("</body>");
		out.print("</html>");
	}
}
