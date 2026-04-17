package baekjoon;

import java.util.Queue;
import java.util.Scanner;

// 미세먼지 안녕!

public class Q17144 {
	
	static int R, C, T;
	static int[][] map, temp;
	static Queue<Integer> queue;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int clean;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		map = new int[R][C];
		
		
		for (int r=0; r<R; r++) {
			for (int c=0; c<C; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] == -1) {
					clean = r;
				}
			}
		}
		
		for (int t=1; t<=T; t++) {
			
			// 1. 미세먼지 확산
			temp = new int[R][C];
			
			for (int r=0; r<R; r++) {
				for (int c=0; c<C; c++) {
					
					if (map[r][c] > 0) {
						int dust = map[r][c];
						int spread = dust / 5;
		                int spreadCount = 0;
		                
						for (int d=0; d<4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc] != -1 ) {
								temp[nr][nc] += spread;
								spreadCount++;
							}
						}
						temp[r][c] += dust - (spread*spreadCount);
					} else if (map[r][c] == -1) {
		                temp[r][c] = -1; // 공기청정기 위치 보존
		            }
				}
			}
			
			for (int r=0; r<R; r++) {
				map[r] = temp[r].clone();
			}
			
			// 2. 공기청정기 작동
			temp = new int[R][C];
			
			int top = clean - 1;
			int bottom = clean;
			
			// 바람 반대방향으로 하나씩 당겨오기!
			
			// 위쪽
			for (int r=top-1; r>0; r--) {
				map[r][0] = map[r-1][0];
			}
			
			for (int c=0; c<C-1; c++) {
				map[0][c] = map[0][c+1];
			}
			
			for (int r=0; r<top; r++) {
				map[r][C-1] = map[r+1][C-1];
			}
			
			for (int c=C-1; c>0; c--) {
				map[top][c] = map[top][c-1];
				if (map[top][c] == -1) map[top][c] = 0;
			}
			
			
			// 아래쪽
			for (int r=bottom+1; r<R-1; r++) {
				map[r][0] = map[r+1][0];
			}
			
			for (int c=0; c<C-1; c++) {
				map[R-1][c] = map[R-1][c+1];
			}
			
			for (int r=R-1; r>bottom; r--) {
				map[r][C-1] = map[r-1][C-1];
			}
			
			for (int c=C-1; c>0; c--) {
				map[bottom][c] = map[bottom][c-1];
				if (map[bottom][c] == -1) map[bottom][c] = 0;
			}

		}
		
		int count = 0;
		for (int r=0; r<R; r++) {
			for (int c=0; c<C; c++) {
				if (map[r][c] > 0) count += map[r][c];
			}
		}
		
		System.out.println(count);
		
	}
		
}
