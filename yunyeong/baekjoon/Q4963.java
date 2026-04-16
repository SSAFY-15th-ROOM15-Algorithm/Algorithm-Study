package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4963 {
	/*
	 * 섬의 개수
	 * 섬 = 그래프의 개수
	 * 땅: 정점
	 * 간선: 8방 탐색을 해서 땅 옆에 땅이 있으면 간선
	 * 그래프 탐색: 연결성 파악
	 */
	
	// 델타 배열
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	static int W, H;
	static int[][] map;  // 2차원 배열 -> 그대로 그래프의 표현으로 사용
	static boolean[][] visited;  // 방문처리 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if(W==0 && H==0)
				return;
			
			map = new int[H][W];
			
			for (int r=0; r<H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c=0; c<W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[H][W];
			
			// 배열을 행 우선 순회하면서
			// 아직 방문하지 않은 1을 만났다면 => 새로운 그래프를 발견한 것임 => cnt++
			// dfs로 탐색하면서, 연결되어있는 1의 visited를 모두 true로 바꾼다
			
			int cnt = 0;
			for (int r=0; r<H; r++) {
				for(int c=0; c<W; c++) {
					if (map[r][c] == 1 && !visited[r][c]) { // 아직 방문하지 않은 노드 발견 = 새로운 그래프 찾음
						cnt++;
						visited[r][c] = true;  // 방문처리 후
						dfs(r, c);  // (r, c)를 기점으로 하여 그래프 탐색 시작
					}
				}
			}
			System.out.println(cnt);
		}
	}
	
	private static void dfs(int r, int c) {
		// (r, c)에 인접하면서 아직 방문하지 않은 노드가 있다면 => 방문처리 후 dfs 재귀호출하여 탐색
		for (int d=0; d<8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if ( nr<0 || nr>=H || nc<0 || nc>=W )
				continue;
			if (map[nr][nc] == 1 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}
