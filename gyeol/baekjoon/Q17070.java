import java.util.Scanner;
public class Q17070 {
	static int N, cnt;
	static int[][] board;
	static int [] dx = {0, 1, 1};
	static int [] dy = {1, 1, 0};
	static int [][] way = {
			{0, 1}, // 가로일 때
			{0, 1, 2}, //대각선일 때
			{1, 2} // 세로일 때
	};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		cnt = 0;
		dfs(0, 1, 0);
		System.out.println(cnt);
		
		
	}
	private static void dfs(int x, int y, int d) {
		if(x == (N - 1) && y == (N - 1)) {
			cnt++;
			return;
		}
		
		for (int dir : way[d]) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			if(board[nx][ny] == 1) continue;
			
			if(dir == 1) { // 대각선
			    if(board[x][y+1] == 1 || board[x+1][y] == 1) continue;
			}
			
			dfs(nx, ny, dir);
		}
	}
}

/*
 가로(0) : 가로(0), 대각선(1)
 세로(2) : 세로(2), 대각선(1)
 대각선(1) : 가로(0), 대각선(1), 세로(2);
 */