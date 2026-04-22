package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q5656_벽돌깨기 {
	
	static int N, W, H;
	static int[][] map;
	static int answer;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static class Node {
		int r;
		int c;
		int power;
		
		Node(int r, int c, int power) {
			this.r = r;
			this.c = c;
			this.power = power;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			
			for (int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = H*W+1;
			dfs(0, map);
			
			System.out.println("#"+tc+" "+answer);
		
		}	
	}
	
	public static void dfs(int n, int[][] map) {
		
		// 남은 벽돌 수 세서 answer 업데이트하기
		int count = 0;
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if (map[i][j]>0) count++;
			}
		}
		answer = Math.min(answer, count);
		
		// 이미 모든 벽돌 다 깨졌으면 그만
		if (answer == 0) return;
		
		// 구슬 N번 다 쐈으면 그만
		if (n == N) {
			return;
		}
			
	
		for (int c=0; c<W; c++) {
			
			// 매턴 원본맵 복사해서 경우의 수 따져봄.
			int[][] copied = new int[H][W];
			for (int i=0; i<H; i++) {
				copied[i] = map[i].clone();
			}
			
			// 구슬이 닿을 맨 위 벽돌 위치 찾기
			int r = -1;
			for (int i=0; i<H; i++) {
				if (copied[i][c] != 0) {
					r = i;
					break;
				}
			}
			
			// 이미 빈 열이면 다음 열로 넘어감
			if (r == -1) continue;
			
			// 깨부셔 -> bfs로 연쇄폭발
			boom(r, c, copied);
			// 중력으로 아래로 정리해
			down(copied);
			// 다음턴 고
			dfs(n+1, copied);
		}
		
	}

	
	static void boom(int r, int c, int[][] copied) {
		
		Queue<Node> q = new ArrayDeque<>();
		
		q.offer(new Node(r, c, copied[r][c]));
		
		copied[r][c] = 0;
		
		while (!q.isEmpty()) {
						
			Node curr = q.poll();
			
			for (int p=1; p<curr.power; p++) {
				for (int d=0; d<4; d++) {
					int nr = curr.r + dx[d]*p;
					int nc = curr.c + dy[d]*p;
					
					// 범위 초과하거나 이미 0인 칸은 패스
					if (nr<0 || nr>=H || nc<0 || nc>=W || copied[nr][nc]==0) continue;
					
					// 1보다 큰애들은 다음 연쇄폭발을 위해 큐에 넣음
					if (copied[nr][nc] > 1)
						q.offer(new Node(nr, nc, copied[nr][nc]));
					
					// 깬 벽돌은 다 0으로
					copied[nr][nc] = 0;
				}
			}
		}		
	}
	
	// 중력으로 내려가서 정렬 ->> 맨 아래부터 차곡차곡 쌓이게
	static void down(int[][] copied) {
		for (int c=0; c<W; c++) {
			int[] temp = new int[H]; // 세로로 한줄짜리 임시배열, 0으로 채워져있음
			int idx = H-1; // 맨뒤(맨밑)부터 채우겠다는 뜻
			
			for (int r=H-1; r>=0; r--) {
				if (copied[r][c] != 0) {
					temp[idx] = copied[r][c];
					idx--;
				}
			}
			// 그러면 temp가 {0, 0, 2, 1, 3} 이런식으로 뒤부터 채워졌겠지?
			// 그걸 다시 copied에 덮어쓰자
			for (int r=0; r<H; r++) {
				copied[r][c] = temp[r];
			}
		}
	}

}
