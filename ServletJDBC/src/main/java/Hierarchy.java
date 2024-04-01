
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hierarchy extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBJob db = new DBJob();
		TreeMap<Integer,User> users = new TreeMap<>(db.executeQueryForFetch("select * from employee"));
		
		HashMap<Integer,Integer> ChildCount = new HashMap<>(); // To count number of direct juniors for each employee
		for(Map.Entry<Integer, User> i:users.entrySet()) {
			ChildCount.put(i.getKey(), 0);
		}
		for(Map.Entry<Integer, User> i:users.entrySet()) {
			if(i.getKey()==1)
				continue;
			ChildCount.put(i.getValue().manager_id, ChildCount.get(i.getValue().manager_id)+1);	
		}
		
		List<List<Integer>> levels = new ArrayList<>(); // Assigning levels so that they can easily placed in the table
		List<Integer> currentLevel = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		currentLevel.add(1);
		levels.add(currentLevel);
		while(!currentLevel.isEmpty()) {
			for(int i:currentLevel) {
				for(Map.Entry<Integer, User> user:users.entrySet()) {
					if(user.getValue().manager_id == i) {
						temp.add(user.getKey());
					}
				}
			}
			currentLevel = temp;
			temp = new ArrayList<Integer>();
			levels.add(currentLevel);	
		}
		
		HashMap<Integer,Integer> colspace = new HashMap<>(); // Calculating space to make colspan in the table
		for(int i=levels.size()-1;i>=0;i--) {
			for(int j=0;j<levels.get(i).size();j++) {
				if(ChildCount.get((levels.get(i)).get(j))==0) {
					colspace.put((levels.get(i)).get(j),1);
				}
				int id = users.get((levels.get(i)).get(j)).manager_id;
				colspace.put(id,colspace.getOrDefault(id,0)+colspace.get((levels.get(i)).get(j)));
				
			}
		}
		for(Map.Entry<Integer, Integer> map:colspace.entrySet()) {
			System.out.print(map.getKey());
			System.out.print(" ");
			System.out.print(map.getValue());
			System.out.println();
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>View Hierarchy</title></head><style>table{text-align:center;padding:5px;}</style><body>");
		out.println("<h3>Employee's hierarchy</h3>");
		out.println("<br><table border=1 style='{text-align:center}'>");
		for(List<Integer> i : levels) {
			out.print("<tr>");
			for(int j : i) {
				out.print("<td colspan="+ String.valueOf(colspace.get(j))+">");
				out.print(users.get(j).name);
				out.print("</td>");
			}
			out.print("</tr>");
		}
		out.print("</table>");
		
		out.println("<form action='home'><button type='submit'>Click here to home page</button></form>");
		out.println("<form action='logout'><button type='submit'>Click here to logout</button></form>");
		out.println("</body></html>");
	
	}
}
