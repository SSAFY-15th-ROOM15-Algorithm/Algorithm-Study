package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1249_보급로 {
	
	static int N;
	static int[][] map;
	static int[][] dist;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Node implements Comparable<Node> {
		int r;
		int c;
		int cost;
		
		Node(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

	}
	
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			dist = new int[N][N];
			
			for (int i=0; i<N; i++) {
				String line = br.readLine();
				for (int j=0; j<N; j++) {
					map[i][j] = line.charAt(j)-'0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			int result = find();
			
			System.out.println("#"+tc+" "+result);
			
		}
		
		
	}
	
	// 다익스트라 알고리즘
	public static int find() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		dist[0][0] = 0;
		pq.offer(new Node(0,0,0));
		
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			int r = curr.r;
			int c = curr.c;
			int cost = curr.cost;
			
			if (cost > dist[r][c]) continue; // 이미 dist에 더 최단거리가 적혀있으면 패스
			
			if (r == N-1 && c == N-1) return cost;  // 처음으로 도착점 좌표의 거리가 나온다? 그게 최단거리임! 반환
			
			for (int d=0; d<4; d++) { // 사방 탐색하면서
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr<0 || nr>=N || nc<0 || nc>=N) continue; // 범위체크하고
				
				int newCost = cost + map[nr][nc];  // 새 거리 = 지금까지 여기 온 거리 + 다음 비용
				
				if (newCost < dist[nr][nc]) {  // 새 거리가 더 적은비용이면 dist에 저장하고, 큐에 넣음
					dist[nr][nc] = newCost;
					pq.offer(new Node(nr, nc, newCost));
				}
			}
			
		}
		
		return dist[N-1][N-1];
				
	}
	
	
}
