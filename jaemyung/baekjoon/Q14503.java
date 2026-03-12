import java.util.*;

public class Q14503 {
	static int n, m;
	static boolean[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		map = new boolean[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int t = sc.nextInt();
				if(t==1) {
					map[i][j] = true;
				}
			}
		}
		int cnt = 0;
		clean(r,c,d);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(visited[i][j]) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
	
	static void clean(int r, int c, int d) {
		visited[r][c] = true;
		int nd = d;
		for (int i = 0; i < 4; i++) {
			nd = (nd+3)%4;
			int nr = r + dr[nd];
			int nc = c + dc[nd];
			
			if(canGo(nr,nc) && !visited[nr][nc]) {
				clean(nr,nc,nd);
				return;
			}
		}

		int br = r - dr[d];
		int bc = c - dc[d];
		if(canGo(br,bc)) {
			clean(br,bc,d);
		}
		
	}
	
	static boolean canGo(int r, int c) {
		if(0<=r && r<n && 0<=c && c<m && !map[r][c]) {
			return true;
		}
		
		return false;
	}
}