package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q7733_치즈도둑 {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			int maxTaste = 0;
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxTaste = Math.max(maxTaste, map[i][j]);
				}
			}
			
			int maxCount = 1; // 첫날에 한덩어리!!! 0 아님
			
			for (int day=1; day<=maxTaste; day++) {
				visited = new boolean[N][N]; //배열 초기화
				int count = 0; // 덩어리 수
				
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						if (!visited[i][j] && map[i][j]>day) {
							dfs(i, j, day); // 연결된 덩어리 탐색
							count++; // 덩어리 하나당 카운트+1
						}
					}
				}
				maxCount = Math.max(maxCount, count);
			}
			
			System.out.println("#"+tc+" "+maxCount);
			
			
		}
		
	}
	
	public static void dfs(int i, int j, int day) {
		
		visited[i][j] = true;
		
		for (int d=0; d<4; d++) {
			int ni = i + dx[d];
			int nj = j + dy[d];
			if (ni<0 || ni>=N || nj<0 || nj>=N) continue;
			if (visited[ni][nj]) continue;
			if (map[ni][nj] <= day) continue;
			dfs(ni, nj, day);
		}

	}
	
	
}
