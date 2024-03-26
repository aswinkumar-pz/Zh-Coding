

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Add extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBUtils.url,DBUtils.username,DBUtils.password);
			PreparedStatement stmt = conn.prepareStatement("insert into employee(name,manager_id) value(?,?)");
			String name = request.getParameter("name");
			int id = Integer.parseInt(request.getParameter("manager-id"));
			stmt.setString(1,name);
			stmt.setInt(2, id);
			try {
				stmt.executeUpdate();
			}
			catch(Exception e){
				conn.close();
				response.sendRedirect("error.html");
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
