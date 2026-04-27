import java.util.*;

public class Q1249 {
	static int N;
	static int [][] map;
	static int [][] dist;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	static class Edge implements Comparable<Edge> {
		int x, y, cost;
		
		public Edge(int x, int y, int cost) {
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
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			// 출발지에서 도착지까지 복구시간이 가장 짧은 경로의 복구 시간 구하기
			// 출발지 : map[0][0], 도착지 : map[N - 1][N - 1]
			// 1 : 복구 작업이 필요한 곳, 0 : 복구 작업 불필요 -> 다익스트라
			N = sc.nextInt();
			map = new int [N][N];
			dist = new int [N][N];
			
			for(int i = 0; i < N; i++) {
				String str = sc.next();
				
				for (int j = 0; j < N; j++) {
			        map[i][j] = str.charAt(j) - '0';
			    }
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			dijkstra();
			
			System.out.println("#" + t + " " + dist[N - 1][N - 1]);
		}
	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0, 0));
		
		dist[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			int x = cur.x;
			int y = cur.y;
			
			if(dist[x][y] < cur.cost) continue;
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
				int newCost = dist[x][y] + map[nx][ny];
				
				if(newCost < dist[nx][ny]) {
					dist[nx][ny] = newCost;
					pq.add(new Edge(nx, ny, newCost));
				}
			}	
		}
		
	}
}
