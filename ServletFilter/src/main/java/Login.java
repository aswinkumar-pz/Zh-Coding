
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<html><head><title>Login page</title></head><body>");
			out.print("<h1>Login page</h1>");
			out.print("<form method='post' action='login'>");
			out.print("<label>Enter your username:</label>");
			out.print("<input type='text' name='username' required><br>");
			out.print("<label>Enter password:</label>");
			out.print("<input type='password' name='password' required><br>");
			out.print("<button type='submit'>Login</button>");
			out.print("</form></body></html>");
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType("text/html");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.print(username);
			System.out.print(password);
			
			if(username.equals("admin") && password.equals("pass")) {
				HttpSession session = request.getSession();
				session.setAttribute("username",username);
				System.out.println("pass");
				response.sendRedirect("home");
			}
			else {
				response.sendRedirect("login");
			}
		}
		

}
