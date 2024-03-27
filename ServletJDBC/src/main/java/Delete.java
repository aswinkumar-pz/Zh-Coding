

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
		List<User> users = null;
		PrintWriter out = response.getWriter();
		try {
			users = EmpDao.getAllEmployeeExceptAdmin();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		out.print("<html><head>");
		out.print("<title> Delete Employee </title>");
		out.print("<head><body>");
		out.print("<h1>Delete employee</h1>");
		out.println("<form method='post' action='delete'>");
		out.println("<label>Select Employee Id to delete</label>");
		out.println("<select name='id'>");
		for(int i=0;i<users.size();i++) {
			out.println("<option value='"+users.get(i).id+"'>"+users.get(i).id+" "+users.get(i).name+" "+"</option>");
		}
		out.println("</select>");
		out.println("<button type='submit'>Delete</button>");
		out.println("</form></body></html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			EmpDao.deleteEmployee(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><head>");
		out.print("<title> Delete Employee </title>");
		out.print("<head><body>");
		out.print("<h1>Employee deleted successfully</h1>");
		out.println("<form action='home.html'><button type='submit'>Click here to home page</button></form>");
		out.print("</body></html>");
		
	}
}
