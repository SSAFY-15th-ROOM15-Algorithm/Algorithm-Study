import java.util.*;

public class Q24220 {
	static int N, E, S, G, cnt;
	static ArrayList<Integer> [] list;
	static boolean [] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 마지막 정점 번호
			E = sc.nextInt(); // 간선 수
			
			list = new ArrayList [N + 1];
			for(int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				list[a].add(b);
			}
			
			S = sc.nextInt(); // 출발 정점
			G = sc.nextInt(); // 도착 정점
			
			visited = new boolean [N + 1];
			visited [S] = true;
			
			cnt = 0;
			dfs(S);
			
			System.out.println("#" + t + " " + cnt);
		}
	}
	private static void dfs(int v) {
		if(v == G) {
			cnt++;
			return;
		}
		
		for(int next : list[v]) {
			if(!visited[next]) {
				visited [next] = true;
				dfs(next);
				visited[next] = false;
			}
		}
	}
}
