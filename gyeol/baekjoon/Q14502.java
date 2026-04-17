import java.util.*;

public class Q14502 {
	static int N, M, ans;
	static int [][] board;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
		N = sc.nextInt();
		M = sc.nextInt();
		
		board = new int [N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		ans = 0;
		wall(0);
		
		
		System.out.println(ans);
		
	}

	private static void wall(int cnt) {
		if(cnt == 3) {
			int max = bfs();
			ans = Math.max(max, ans);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] == 0) {
					board[i][j] = 1;
					wall(cnt + 1);
					board[i][j] = 0;
				}
			}
		}
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
		    temp[i] = board[i].clone();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if (board[i][j] == 2) q.offer(new int[] {i, j});
			}
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];

			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(temp[nx][ny] == 0) {
					temp[nx][ny] = 2;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(temp[i][j] == 0) cnt++;
			}
		}
		
		return cnt;
	}
}
