

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Add extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		String name = request.getParameter("name");
		int manager_id = Integer.parseInt(request.getParameter("manager_id"));
		DBJob db = new DBJob();
		String query = "insert into employee(name,manager_id) value('"+name+"',"+manager_id+")";
		db.executeUpdateQuery(query);
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Success</title></head><body>");
		out.println("<h3>Employee details added successfully</h3>");
		out.println("<form action='home'><button type='submit'>Click here to home page</button></form>");
		out.println("<form action='logout'><button type='submit'>Click here to logout</button></form>");
		out.println("</body></html>");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		DBJob db = new DBJob();
		HashMap<Integer,User> hm = new HashMap<>();
		hm = db.executeQueryForFetch("select * from employee");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Enter employee details</title></head><body>");
		out.println("<h1>Enter employee details</h1>");
		out.println("<form method='post' action='add'>");
		out.println("<label>Enter employee name</label><input type='text' name='name'>");
		out.println("<label>Select manager-id</label>");
		out.println("<select name='manager_id'>");
		for(Map.Entry<Integer,User> i:hm.entrySet()) {
			out.println("<option value='"+i.getKey()+"'>"+(i.getValue()).name+"</option>");
		}
		out.println("</select>");
		out.println("<input type='submit'>Submit</input>");
		out.println("</form></body></html>");
	}
}
