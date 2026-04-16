package baekjoon;

import java.util.Scanner;

public class Q14503 {
	
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};  // 북 동 남 서
	static int[] dc = {0, 1, 0, -1};
	static int clean;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
				
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		clean = 0;
		robot(r, c, d);
		
		System.out.println(clean);
		
	}
	
	
	public static void robot(int r, int c, int d) {
		if (map[r][c] == 0) {  // 우선 현재 칸 청소함
			map[r][c] = 2;
			clean++;
		}
		
		for (int i=d+3; i>=d; i--) {  // 반시계 방향으로 회전하면서 상하좌우에 청소할 칸 있나 봄
			
			int nr = r + dr[i%4];
			int nc = c + dc[i%4];
			if (map[nr][nc] == 0) {  // 청소할 칸 있으면 거기로 전진
				r = nr;
				c = nc;
				d = i%4;
				robot(r, c, d);
				return;
			}
		}
		
		// 다 봤는데 청소할 칸 없으면 -> 한칸 후진해야함
		
		int br = r + dr[(d+2)%4];
		int bc = c + dc[(d+2)%4];
		if (map[br][bc] != 1) {  // 뒤가 벽이 아니면 한칸 후진
			r = br;
			c = bc;
			robot(r, c, d);
		} else return;  // 뒤가 벽이면 거기서 끝
		
		
	}
	
}
