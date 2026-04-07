package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 토마토 문제(그래프탐색)

public class Q7576 {
	
	static int M, N;
	static Queue<int[]> q;
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		arr = new int[N][M];
		q = new LinkedList<>();
		
		// 1: 익은 토마토, 0: 안 익은 토마토, -1: 빈칸
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				int num = sc.nextInt();
				arr[i][j] = num;
				if (num == 1) {
					q.offer(new int[]{i,j}); // 익은 토마토 좌표만 넣어둠
				}
			}
		}
		
		bfs();
		
		int max = 0;

		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < M; j++) {
		        if (arr[i][j] == 0) {
		            System.out.println(-1);
		            return;
		        }
		        
		        max = Math.max(max, arr[i][j]);
		    }
		}

		System.out.println(max - 1);
		
	}
	
	
	static void bfs() {
		while (!q.isEmpty()) {
			int [] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue; // 범위 초과 시 넘어감
				
				// 상하좌우에 안 익은 토마토가 있으면~!
				if (arr[nx][ny] == 0) {
					arr[nx][ny] = arr[x][y] + 1; // 이 칸의 토마토가 익은 날!
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
	
	
}
