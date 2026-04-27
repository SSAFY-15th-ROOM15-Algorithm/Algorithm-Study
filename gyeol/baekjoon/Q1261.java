import java.util.*;

public class Q1261 {
	static int N, M;
	static int [][] board;
	static int [][] dist;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	static class Edge implements Comparable<Edge>{
		int x, y, cost;
		
		public Edge(int x, int y, int cost){
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		board = new int[N][M];
		dist = new int [N][M];
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			String str = sc.next();
			
			for(int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j) - '0';
			}
		}
		
		dijk();
		System.out.println(dist[N - 1][M - 1]);
	}

	private static void dijk() {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.offer(new Edge(0, 0, 0));
		
		dist[0][0] = 0;
		
		while(!q.isEmpty()) {
			Edge cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int cost = cur.cost;
			
			if(dist[x][y] < cost) continue;
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				
				int newCost = dist[x][y] + board[nx][ny];
				if(newCost < dist[nx][ny]) {
					dist[nx][ny] = newCost;
					q.offer(new Edge(nx, ny, newCost));
				}
			}
		}
	}
}
