import java.util.*;

public class Safe_zone {
	static int N, M;
	static int [][] board;
	static boolean [][] visited;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int [N][M];
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
				max = Math.max(max, board[i][j]);
			}
		}
		
		int K = max;
		int ans = 0;
		for(int k = 1; k <= max; k++) {
			visited = new boolean [N][M];
			int cnt = 0;
			for(int i = 0; i < N; i++){
				for(int j = 0; j < M; j++) {
					if(board[i][j] > k && !visited[i][j]) {
						dfs(i, j, k);
						cnt++;
					}
				}
			}
			if(ans < cnt) {
				ans = cnt;
				K = k;
			}
			else if(cnt == ans) {
				K = Math.min(K, k);
			}
		}
		
		System.out.println(K + " " + ans);
	}

	private static void dfs(int x, int y, int k) {
		visited[x][y] = true;
		
		for(int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			if(board[nx][ny] <= k || visited[nx][ny]) continue;
			
			dfs(nx, ny, k);
		}
	}
}
