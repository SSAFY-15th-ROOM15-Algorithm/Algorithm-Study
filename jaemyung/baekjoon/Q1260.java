import java.util.*;

public class Q1260 {
	static int n, m, s;
    static boolean[][] graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();
        
        graph = new boolean[n+1][n+1];
        for (int i = 0; i < m; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			
			graph[r][c] = true;
			graph[c][r] = true;
		}
        visited = new boolean[n + 1];
        dfs(s);
        System.out.println();
        visited = new boolean[n + 1];
        bfs(s);
    }
    
    static void dfs(int r) {
    	visited[r] = true;
    	System.out.print(r + " ");
    	for (int i = 1; i < n+1; i++) {
			if(graph[r][i] && !visited[i]) {
				dfs(i);
			}
		}
    }
    
    static void bfs(int r) {
        Queue<Integer> q = new LinkedList<>();
        
        q.offer(r);
        visited[r] = true; 

        while (!q.isEmpty()) {
            int x = q.poll();
            System.out.print(x + " ");

            for (int i = 1; i <= n; i++) {
                if (graph[x][i] && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}