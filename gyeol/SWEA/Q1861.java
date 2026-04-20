import java.util.*;

public class Q1861 {
	static int N, first, max;
	static int [][] board;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			board = new int [N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			
			first = Integer.MAX_VALUE;
			max = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}
			
			System.out.println("#" + t + " " + first + " " + max);
		}
	}

	private static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean [][] visited = new boolean[N][N];
		
		q.add(new int[] {i, j});
		visited[i][j] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			
			int x = cur[0];
			int y = cur[1];
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				if(board[nx][ny] != board[x][y] + 1) continue;
				
				visited[nx][ny] = true;
				q.add(new int [] {nx, ny});
				cnt++;
			}
		}
		
		if(cnt > max) {
			max = cnt;
			first = board[i][j];
		}
		else if(cnt == max) {
			first = Math.min(first, board[i][j]);
		}
		
	}
}
