import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Ice {
	static int N, M, cnt, time;
	static int [][] board;
	static boolean [][] visited;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int [N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt(); // 1: 빙하 , 0 : 물
			}
		}
		
		cnt = 0;
		time = 0;
		
		while(true) {
			int ice = countIce();
			if(ice == 0) break;
			cnt = ice;
			
			bfs();
			
			time++;
		}
		
		System.out.println(time + " " + cnt);
		
	}

	private static int countIce() {
		int cnt = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] == 1) cnt++;
			}
		}
		return cnt;
	}

	private static void bfs() {
		Queue<int []> q = new ArrayDeque<>();
		q.add(new int [] {0, 0});
		
		visited = new boolean [N][M];
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny] = true;
				
				// 물일 경우
				if(board[nx][ny] == 0) {
					q.add(new int [] {nx, ny});
				}
				// 빙하일 경우
				else if(board[nx][ny] == 1) {
					board[nx][ny] = 0;
				}
			}
		}
	}
}
