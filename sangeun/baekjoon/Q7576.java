import java.util.*;

public class Q7576 {
	
	static int M, N;
	static int [][] box;
	static Deque<int[]> q = new ArrayDeque<>();
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void bfs() {
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			int r = cur[0];
			int c = cur[1];
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				    if (box[nr][nc] == 0) {
				        box[nr][nc] = box[r][c] + 1;
				        q.offer(new int[]{nr, nc});
				    }
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		box = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				box[i][j] = sc.nextInt();
				
				if(box[i][j] == 1) {
					q.offer(new int[] {i,j});
				}
			}
		}
		bfs();
		
		int max = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				
				if(box[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				
				max = Math.max(max, box[i][j]);
			}
		}
		
		System.out.println(max-1);
	}
}