import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class UnionFind {
	
	private int[] parentNode; // parent of the node.
	private int[] size; // component size.
	private int component; // number of members in the Graph.
	
	public UnionFind(int n){
		component = n;
		parentNode = new int[n+1];
		size = new int[n+1];
		for (int i = 1 ; i <= n ; i ++){
			parentNode[i] = i;
			size[i] = 1;
		}
	}
	
	public int getParent(int node){
		return parentNode[node];
	}
	
	public boolean connected(int node1, int node2){
		return parentNode[node1] == parentNode[node2];
	}
	
	public void union(int node1, int node2){
		if (getParent(node1) == getParent(node2))
			return;
		if (size[getParent(node1)] <= size[getParent(node2)]){
			parentNode[getParent(node1)] = parentNode[getParent(node2)];
			size[getParent(node2)] += size[getParent(node1)];
		}else{
			parentNode[getParent(node2)] = parentNode[getParent(node1)];
			size[getParent(node1)] += size[getParent(node2)];
		}
		component --;
		System.out.println("Component:  " + getNumOfComponent());

		System.out.println("Unioned " + node1 + " and "+ node2 + "!!!!!!!!!!");
		System.out.println("");
	}
	
	public int getNumOfComponent(){
		return component;
	}
	
	
	
	
	
	
	public static void main(String[] args) throws Exception{
		
		ArrayList<Friendship> data = new ArrayList<Friendship>();
		ArrayList<Integer> persons = new ArrayList<Integer>();
		
		String s = "data.txt";
		File file = new File(s);
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String da = bf.readLine();
		while (da != null){
			String fiels[] = da.split(" ");
			int p1 = Integer.parseInt(fiels[0]);
			int p2 = Integer.parseInt(fiels[1]);
			Friendship f = new Friendship(p1,p2,fiels[2]);
			data.add(f);
			da = bf.readLine();
			if (!persons.contains(p1))
				persons.add(p1);
			if (!persons.contains(p2))
				persons.add(p2);
		}
		
	
		
		
		 UnionFind uf = new UnionFind(persons.size());
		 int counter = 0;
		
		while (uf.getNumOfComponent() != 1){
			int p1 = data.get(counter).getPerson1();
			int p2 = data.get(counter).getPerson2();
			if (uf.connected(p1, p2))
				continue;
			uf.union(p1, p2);
			counter ++;
	}
		
		System.out.println("The social network is fully connected at " + data.get(counter-1).getTime());
	}
}
