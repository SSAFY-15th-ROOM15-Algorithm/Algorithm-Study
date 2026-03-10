import java.util.*;

public class Q7576 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		int [][] box = new int[N][M];
		Queue<int[]> q = new LinkedList<>(); // 익은 토마토 위치
		
		int [] dx = {-1, 1, 0, 0};
		int [] dy = {0, 0, -1, 1};
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				box[i][j] = sc.nextInt();
				if(box[i][j] == 1) q.offer(new int [] {i, j});
			}
		}
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int x = cur[0]; int y = cur[1];
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(box[nx][ny] == 0) {
					box[nx][ny] = box[x][y] + 1;
					q.add(new int[] {nx, ny});
				}
				
			}
		}
		
		int day = 0;
		boolean green = false;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(box[i][j] == 0) green = true;
				day = Math.max(day, box[i][j]);
			}
		}
		if(green) System.out.println(-1);
		else System.out.println(day - 1);
	}
}