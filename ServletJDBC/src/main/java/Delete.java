

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBJob db = new DBJob();
		TreeMap<Integer,User> users = new TreeMap<>(db.executeQueryForFetch("select * from employee where id!=1"));
		
		out.print("<html><head>");
		out.print("<title> Delete Employee </title>");
		out.print("<head><body>");
		out.print("<h1>Delete employee</h1>");
		out.println("<form method='post' action='delete'>");
		out.println("<label>Select Employee Id to delete</label>");
		out.println("<select name='id'>");
		for(Map.Entry<Integer,User> i : users.entrySet() ) {
			out.println("<option name='manager_id' value="+i.getKey()+">"+(i.getValue()).name+"-"+i.getKey()+"</option>");
		}
		out.println("</select>");
		out.println("<button type='submit'>Delete</button>");
		out.println("</form></body></html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		DBJob db = new DBJob();
		HashMap<Integer,User> hm = new HashMap<>();
		hm = db.executeQueryForFetch("select * from employee where id="+id);
		int manager_id = hm.get(id).manager_id;
		db.executeUpdateQuery("update employee set manager_id="+manager_id+" where manager_id="+id);
		db.executeUpdateQuery("delete from employee where id="+id);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><head>");
		out.print("<title> Delete Employee </title>");
		out.print("<head><body>");
		out.print("<h1>Employee deleted successfully</h1>");
		out.println("<form action='home'><button type='submit'>Click here to home page</button></form>");
		out.println("<form action='logout'><button type='submit'>Click here to logout</button></form>");
		out.print("</body></html>");
		
	}
}
