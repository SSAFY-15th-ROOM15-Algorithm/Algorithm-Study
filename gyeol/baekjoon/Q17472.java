import java.util.*;

public class Q17472 {
	static int N;
	static int [][] map;
	static ArrayList<Edge> edges;
	static int [] p;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	static class Edge implements Comparable<Edge>{
		int from, to, cost;
		
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt(); // 0 : 바다, 1 : 육지
				}
			}
			
			int landId = 2;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						dfs(i, j, landId); // 1. 섬 라벨링
						landId++;
					}
				}
			}
			
			findBridge(); // 다리 후보
			int ans = mst(landId);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void dfs(int x, int y, int landId) {
		for(int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] == 1) {
	            dfs(nx, ny, landId);
	        }
		}
	}
	
	private static void findBridge() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] >= 2) {
					int from = map[i][j];
					
					for(int dir = 0; dir < 4; dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						int len = 0;
						
						while(nx >= 0 && ny >= 0 && nx < N && ny < N) {
							if(map[nx][ny] == 0) {              // 1. 바다일 때 : 계속 직진 + 길이 증가
								nx += dx[dir];
								ny += dy[dir];
								len++;
							}
							else if(map[nx][ny] == from) break;// 2. 같은 섬일 때
							else{                              // 3. 다른 섬일 때 : 간선 추가
								int to = map[nx][ny];
								if(len >= 2) edges.add(new Edge(from, to, len));// 다리 길이가 2 이상일 때만 다리 연결 가능
								break;
							}
						}
					}
				}
			}
		}
	}
	
	private static int mst(int landId) {
		p = new int [landId];
		
		for(int i = 0; i < landId; i++) p[i] =  i; // 자기 자신이 대표
		Collections.sort(edges);// 정렬
		
		int total = 0; // 비용
		int cnt = 0; // 간선 수
		
		for(Edge e : edges) {
			int px = find(e.from);
			int py = find(e.to);
			
			if(px != py) { // 서로 다른 집합 일 때 연결 가능
				p[px] = py;
				
				total += e.cost;
				cnt++;
			}
		}
		int landCnt = landId - 2;
		if(cnt == landCnt - 1) return total;
		else return  - 1;
	}

	private static int find(int x) {
		if(x != p[x]) { // 자기 자신이 아닐 경우
			p[x] = find(p[x]); // 경로 압축
		}
		return p[x];
	}
}
