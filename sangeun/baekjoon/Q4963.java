import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q4963{
	static int w, h;
	static int[][] map;
	static boolean[][] visited;
	
	static int [] dr = {-1, 1, 0, 0, -1, -1, 1 , 1};
	static int [] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	
	static class Pos{
		int r, c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			w = sc.nextInt();
			h = sc.nextInt();
			
			if(w==0 && h==0) break;
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int ans = 0;
			
			for(int r=0; r<h; r++) {
				for(int c=0; c<w; c++) {
					if(map[r][c] == 1 && !visited[r][c]) {
						bfs(r,c);
						ans++;
					}
				}
			}
			System.out.println(ans);
		}
	}
	
	static void bfs(int r, int c) {
		Queue<Pos> q = new LinkedList<>();
		
		q.add(new Pos(r,c));
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			
			Pos p = q.poll();
			
			for(int d=0; d<8; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] == 0) continue;
				
				visited[nr][nc] = true;
				q.add(new Pos(nr, nc));
			}
		}
	}
}


