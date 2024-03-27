
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hierarchy extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>View Hierarchy</title></head><style>table{text-align:center;padding:5px;}</style><body>");
		out.println("<h3>Employee's hierarchy</h3>");
		out.println("<br><table border=1 style='{text-align:center}'>");

		List<User> users = null;
		
		try {
			users = EmpDao.getAllEmployee();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HashMap<Integer,Integer> ChildCount = new HashMap<>();
		for(User user:users) {
			ChildCount.put(user.id,0);
		}
		
		for(User user:users) {
			if(user.id==1)
				continue;
			ChildCount.put(user.manager_id,ChildCount.get(user.manager_id)+1);
		}
		
		List<List<Integer>> levels = new ArrayList<>();
		List<Integer> currentLevel = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		currentLevel.add(1);
		levels.add(currentLevel);
		
		while(!currentLevel.isEmpty()) {
			for(int i:currentLevel) {
				for(User user : users) {
					if(user.manager_id == i) {
						temp.add(user.id);
					}
				}
			}
			currentLevel = temp;
			temp = new ArrayList<Integer>();
			levels.add(currentLevel);	
		}
		
		HashMap<Integer,Integer> colspace = new HashMap<>();
		
		for(int i=levels.size()-1;i>=0;i--) {
			for(int j=0;j<levels.get(i).size();j++) {
				if(ChildCount.get((levels.get(i)).get(j))==0) {
					colspace.put((levels.get(i)).get(j),1);
				}
				try {
					int id = (EmpDao.getEmployeeDetailsById((levels.get(i)).get(j))).manager_id;
					colspace.put(id,colspace.getOrDefault(id,0)+colspace.get((levels.get(i)).get(j)));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(Map.Entry<Integer,Integer> map : colspace.entrySet()) {
			System.out.print(map.getKey());
			System.out.print(" ");
			System.out.print(map.getValue());
			System.out.println();
		}
		for(List<Integer> i : levels) {
			out.print("<tr>");
			for(int j : i) {
				out.print("<td colspan="+ String.valueOf(colspace.get(j))+">");
				try {
					out.print(EmpDao.getEmployeeDetailsById(j).name);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				out.print("</td>");
			}
			out.print("</tr>");
		}
		out.print("</table>");
		
		out.println("<form action='home.html'><button type='submit'>Click here to home page</button></form>");
		out.println("</body></html>");
	
	}
}
