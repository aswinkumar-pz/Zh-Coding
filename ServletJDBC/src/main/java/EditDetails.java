

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int manager_id = Integer.parseInt(request.getParameter("manager_id"));
		String name = request.getParameter("name");
		DBJob db = new DBJob();
		db.executeUpdateQuery("update employee set name='"+name+"', manager_id="+manager_id+" where id="+id);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<html><head>");
		out.print("<title> Edit Employee </title>");
		out.print("<head><body>");
		out.print("<h2>Data edited successfully</h2>");
		out.println("<form action='home'><button type='submit'>Click here to home page</button></form>");
		out.println("<form action='logout'><button type='submit'>Click here to logout</button></form>");
		out.print("</body></html>");
		
	}

}
