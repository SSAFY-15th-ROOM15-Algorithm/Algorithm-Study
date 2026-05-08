import java.util.*;

public class rock_climbing {
	static int N, M, startX, startY, endX, endY;
	static int [][] board;
	static int [][] dist;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			board = new int[N][M];
			dist = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
				for(int j = 0; j < M; j++) {
					
					board[i][j] = sc.nextInt(); // 0 : 점프, 1: 안전
					if(board[i][j] == 2) {
						startX = i; startY = j;
					}
					else if(board[i][j] == 3) {
						endX = i; endY = j;
					}
				}
			}
			
			int ans = bfs();
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int [] {startX, startY});
		dist[startX][startY] = 0;
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for(int dir = 0; dir < 4; dir++) {
			    // 좌우 이동
			    if(dir >= 2) {
			        int nx = x + dx[dir];
			        int ny = y + dy[dir];

			        if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			        if(board[nx][ny] == 0) continue;

			        if(dist[nx][ny] > dist[x][y]) {
			            dist[nx][ny] = dist[x][y];
			            q.offer(new int[] {nx, ny});
			        }
			    }

			    // 상하 이동
			    else {
			        for(int nx = 0; nx < N; nx++) {

			        	if(nx == x) continue;
			            if(board[nx][y] == 0) continue;

			            int jump = Math.abs(nx - x);
			            int newCost = Math.max(dist[x][y], jump);

			            if(dist[nx][y] > newCost) {
			                dist[nx][y] = newCost;
			                q.offer(new int[] {nx, y});
			            }
			        }
			    }
			}
		}
		return dist[endX][endY];
	}
}
