

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Edit extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DBJob db = new DBJob();
		HashMap<Integer,User> users = new HashMap<>();
		users = db.executeQueryForFetch("select * from employee");
		db.closeConnections();
		
		out.print("<html><head>");
		out.print("<title> Edit Employee </title>");
		out.print("<head><body>");
		out.print("<h1>Edit employee</h1>");
		out.println("<form method='post' action='edit'>");
		out.println("<label>Select Employee Id to edit</label>");
		out.println("<select name='id'>");
		for(Map.Entry<Integer,User> user : users.entrySet() ) {
			out.println("<option name='id' value='"+user.getKey()+"'>"+(user.getValue()).name+"-"+user.getKey()+" "+"</option>");
		}
		out.println("</select>");
		out.println("<button type='submit'>Edit</button>");
		out.println("</form></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		TreeMap<Integer,User> users;
		
		DBJob db = new DBJob();
		users = new TreeMap<>(db.executeQueryForFetch("select * from employee"));
		db.closeConnections();
		User user = users.get(id);
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(id);
		users.remove(id);
		while (!queue.isEmpty()) {
		    int currentId = queue.poll();
		    Iterator<Map.Entry<Integer, User>> iterator = users.entrySet().iterator();
		    while (iterator.hasNext()) {
		        Map.Entry<Integer, User> i = iterator.next();
		        if (i.getValue().manager_id == currentId) {
		            queue.add(i.getKey());
		            iterator.remove();
		        }
		    }
		}	
		out.print("<html><head>");
		out.print("<title> Edit Employee </title>");
		out.print("<head><body>");
		out.print("<form method='post' action='edited'>");
		out.print("<label>Enter the name</label>");
		out.print("<input type='text' name='name' value="+user.name+">");
		out.print("<label>Select your manager-id</label>");
		out.print("<select name='manager_id'>");
		for(Map.Entry<Integer,User> i : users.entrySet() ) {
			if(i.getKey()==user.manager_id) {
				out.println("<option name='manager_id' value="+i.getKey()+" selected>"+(i.getValue()).name+"-"+i.getKey()+"</option>");
			}
			else {
				out.println("<option name='manager_id' value="+i.getKey()+">"+(i.getValue()).name+"-"+i.getKey()+"</option>");
			}
		}
		out.println("<input type='hidden' name='id' value="+id);
		out.println("</select>");
		out.println("<button type='submit'>Edit details</button></form>");
	}
}
