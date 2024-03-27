

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Edit extends HttpServlet {
	
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
		out.print("<title> Edit Employee </title>");
		out.print("<head><body>");
		out.print("<h1>Edit employee</h1>");
		out.println("<form method='post' action='edit'>");
		out.println("<label>Select Employee Id to delete</label>");
		out.println("<select name='id'>");
		for(int i=0;i<users.size();i++) {
			out.println("<option value='"+users.get(i).id+"'>"+users.get(i).id+" "+users.get(i).name+" "+"</option>");
		}
		out.println("</select>");
		out.println("<label>Enter new name:</label>");
		out.println("<input type='text' name='name'>");
		out.println("<select name='manager_id'>");
		for(int i=0;i<users.size();i++) {
			out.println("<option value='"+users.get(i).id+"'>"+users.get(i).id+" "+users.get(i).name+" "+"</option>");
		}
		out.println("</select>");
		out.println("<button type='submit'>Edit</button>");
		out.println("</form></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><head>");
		out.print("<title> Edit Employee </title>");
		out.print("<head><body>");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int manager_id = Integer.parseInt(request.getParameter("manager_id"));
		HashMap<Integer,User> managers = null;
		
		try {
			managers = EmpDao.getAllEmployeeExceptJunior(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(manager_id == id) {
			out.print("<h1>Invalid id selection!!!</h1>");
			out.print("<h2>You cant select the same employee id for manager</h2>");
			out.println("<form action='home.html'><button type='submit'>Click here to home page</button></form>");
			out.println("<form action='edit'><button type='submit'>Click here to edit again</button></form>");
		}
		else if(!managers.containsKey(manager_id)){
			out.print("<h1>Invalid id selection!!!</h1>");
			out.print("<h2>You cant select the juniors of this employee</h2>");
			out.println("<form action='home.html'><button type='submit'>Click here to home page</button></form>");
			out.println("<form action='edit'><button type='submit'>Click here to edit again</button></form>");
		}
		else {
			User user = new User();
			user.id = id;
			user.name = name;
			user.manager_id = manager_id;
			try {
				EmpDao.editEmployeeDetails(user);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			out.print("<h1>Employee edited successfully</h1>");
			out.println("<form action='home.html'><button type='submit'>Click here to home page</button></form>");
		}
	}

}
