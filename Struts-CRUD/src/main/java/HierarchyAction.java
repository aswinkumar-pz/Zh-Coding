import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.opensymphony.xwork2.ActionSupport;

public class HierarchyAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private DBJob db = new DBJob();
	private TreeMap<Integer,User> users;
	private HashMap<Integer,Integer> colspace = new HashMap<>(); 
	private List<List<Integer>> levels;
	private HashMap<Integer,Integer> ChildCount;
	
	public DBJob getDb() {
		return db;
	}
	
	public void setDb(DBJob db) {
		this.db = db;
	}
	
	public TreeMap<Integer,User> getUsers() {
		return users;
	}
	
	public void setUsers(TreeMap<Integer,User> users) {
		this.users = users;
	}
	
	public HashMap<Integer, Integer> getColspace() {
		return colspace;
	}

	public void setColspace(HashMap<Integer, Integer> colspace) {
		this.colspace = colspace;
	}

	public List<List<Integer>> getLevels() {
		return levels;
	}

	public void setLevels(List<List<Integer>> levels) {
		this.levels = levels;
	}

	public HashMap<Integer, Integer> getChildCount() {
		return ChildCount;
	}

	public void setChildCount(HashMap<Integer, Integer> childCount) {
		ChildCount = childCount;
	}

	public String execute() {
		
		users = new TreeMap<>(db.executeQueryForFetch("select * from employee"));
		
		ChildCount = new HashMap<>(); // To count number of direct juniors for each employee
		for(Map.Entry<Integer, User> i:users.entrySet()) {
			ChildCount.put(i.getKey(), 0);
		}
		for(Map.Entry<Integer, User> i:users.entrySet()) {
			if(i.getKey()==1)
				continue;
			ChildCount.put(i.getValue().manager_id, ChildCount.get(i.getValue().manager_id)+1);	
		}
		
		levels = new ArrayList<>(); // Assigning levels so that they can easily placed in the table
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

		// Calculating space to make colspan in the table
		for(int i=levels.size()-1;i>=0;i--) {
			for(int j=0;j<levels.get(i).size();j++) {
				if(ChildCount.get((levels.get(i)).get(j))==0) {
					colspace.put((levels.get(i)).get(j),1);
					
				}
				int id = users.get((levels.get(i)).get(j)).manager_id;
				colspace.put(id,colspace.getOrDefault(id,0)+colspace.get((levels.get(i)).get(j)));	
			}
		}
		return SUCCESS;
	}

}
