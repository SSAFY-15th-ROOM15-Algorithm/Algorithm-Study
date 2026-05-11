import java.util.*;

public class Q1267 {
	static int V, E;
	static ArrayList<Integer> [] list;
	static ArrayList<Integer> ans;
	static int [] indegree;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int t = 1; t <= 10; t++) {
			V = sc.nextInt(); // 정점의 개수
			E = sc.nextInt(); // 간선의 개수
			
			list = new ArrayList[V + 1];
			for(int i = 1; i <= V; i++) {
				list[i] = new ArrayList<>();
			}
			
			ans = new ArrayList<>();
			indegree = new int[V + 1];
			
			for(int i = 0; i < E; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				list[a].add(b);
				indegree[b]++;
			}
			
			bfs();
			
			System.out.print("#" + t + " ");
			for(int i : ans) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= V; i++) {
			if(indegree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int x = q.poll();
			ans.add(x);
			
			for(int y : list[x]) {
				indegree[y]--;
				if(indegree[y] == 0) q.add(y);
			}
		}
	}
}
