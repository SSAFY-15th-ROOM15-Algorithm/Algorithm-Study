package codeTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 나이트는 다음과 같이 노란색 위치를 기준으로 검은색 8곳으로 움직임이 가능합니다.
 * N×N 격자 위에서 격자를 벗어나지 않고 나이트가 시작점에서 도착점까지 가는 데 걸리는 최소 이동 횟수를 구하는 프로그램을 작성해보세요.
 */

public class day5_1_나이트 {
	static int N;
	static int[][] dist;  // 시작점으로부터의 거리를 저장할 배열
	static int[] dr = {-2, -2, -1, 1, 2, 2, 1, -1};
	static int[] dc = {-1, 1, 2, 2, 1, -1, -2, -2};
	static int startR, startC, endR, endC;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		dist = new int[N+1][N+1];
		
		for (int i=0; i<=N; i++) {
            Arrays.fill(dist[i], -1);  // 초기값 -1로 세팅
        }
		
		startR = sc.nextInt(); startC = sc.nextInt();
        endR = sc.nextInt(); endC = sc.nextInt();
		
        int result = bfs();
		
        System.out.println(result);

	}
	
	 
	public static int bfs() {
		
		Queue<int[]> queue = new LinkedList<>();
		
		// 시작점 넣기
		queue.add(new int[]{startR, startC});
		dist[startR][startC] = 0;    // 시작점은 거리가 0임
		
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			// 여긴 목적지인감?
			if (r == endR && c == endC) {
				return dist[r][c]; //맞으면 여기까지 걸린 거리 반환
			}
			
			// 움직일 수 있는 8방향 탐색
			for (int i=0; i<8; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 범위(1~N) 내에 있는지 확인
				if (nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
					// 그리고 방문한 적 없으면
					if (dist[nr][nc] == -1 ) {
						// 여기 거리 갱신! 이전 거리 +1
						dist[nr][nc] = dist[r][c] + 1;
						// 다음 턴 탐색을 위해 큐에 넣어줌
						queue.add(new int[] {nr, nc});
					}
				}	
			}
		}
		
		return -1; // 아 안되던데???
		
		
	}
	
	
	
}
