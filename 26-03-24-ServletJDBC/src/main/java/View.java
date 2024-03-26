

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.print("<html><head>");
		pw.print("<title> View details </title>");
		pw.print("</head><body>");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBUtils.url,DBUtils.username,DBUtils.password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee");
			while(rs.next()) {
				int c = rs.getInt(1);
				String name = rs.getString(2);
				pw.print(c);
				pw.print(" ");
				pw.print(name);
				pw.print("<br>");
			}
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pw.print("</body>");
		pw.print("</html>");
	}
}
