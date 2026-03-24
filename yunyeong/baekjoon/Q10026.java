package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 적록색맹 문제

public class Q10026 {
	
	// 델타 배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	static char[][] map1;
	static char[][] map2;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map1 = new char[N][N]; // 주어진 그림
		map2 = new char[N][N]; // 적록색약이 보는 그림
		
		// 입력받기
		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<N; j++) {
				char color = str.charAt(j);
				map1[i][j] = color;
				if (color == 'G') color='R';
				map2[i][j] = color;
			}
		}
		
		// 적록색맹 아닌 사람이 보는 구역
		visited = new boolean[N][N];
		int cnt1 = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					cnt1++;
					visited[i][j] = true;
					dfs(i, j, map1);
				}
			}
		}
		
		// 적록색맹인 사람이 보는 구역
		visited = new boolean[N][N];
		int cnt2 = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					cnt2++;
					visited[i][j] = true;
					dfs(i, j, map2);
				}
			}
		}		
		
		// 출력
		System.out.println(cnt1 + " " + cnt2);
		
	}
	
	private static void dfs(int i, int j, char[][] map) {
		for (int d=0; d<4; d++) { // 상하좌우 탐색
			int ni = i + dr[d];
			int nj = j + dc[d];
			
			if ( ni<0 || ni>=N || nj<0 || nj>=N )  // 범위 확인
				continue;
			
			if (map[ni][nj] == map[i][j] && !visited[ni][nj]) {
				visited[ni][nj] = true;
				dfs(ni, nj, map);
			}
			
		}
	}
	
	
}
