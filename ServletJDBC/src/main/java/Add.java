

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Add extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		String name = request.getParameter("name");
		int manager_id = Integer.parseInt(request.getParameter("manager_id"));
		try {
			EmpDao.addEmployee(name, manager_id);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Success</title></head><body>");
		out.println("<h3>Employee details added successfully</h3>");
		out.println("<form action='home'><button type='submit'>Click here to home page</button></form>");
		out.println("<form action='logout'><button type='submit'>Click here to logout</button></form>");
		out.println("</body></html>");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			List<Integer> manager_id = EmpDao.getAllEmployeeId();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>Enter employee details</title></head><body>");
			out.println("<h1>Enter employee details</h1>");
			out.println("<form method='post' action='add'>");
			out.println("<label>Enter employee name</label><input type='text' name='name'>");
			out.println("<label>Select manager-id</label>");
			out.println("<select name='manager_id'>");
			for(int i=0;i<manager_id.size();i++) {
				out.println("<option value='"+manager_id.get(i)+"'>"+manager_id.get(i)+"</option>");
			}
			out.println("</select>");
			out.println("<input type='submit'>Submit</input>");
			out.println("</form></body></html>");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
