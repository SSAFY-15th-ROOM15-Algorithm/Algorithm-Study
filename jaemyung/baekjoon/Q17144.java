import java.util.*;

public class Q17144 {
	static int n, m, t, air;
	static int[][] map;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		t = sc.nextInt();
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==-1) {
					air=i;
				}
			}
		}
		
		for (int i = 0; i < t; i++) {
			int[][] tmp = new int[n][m];
			for (int j = 0; j < n; j++) {
				tmp[j] = map[j].clone();
			}
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					int t = map[j][k]/5;
					if(t>0) {
						for (int l = 0; l < 4; l++) {
							int nr = j+dr[l];
							int nc = k+dc[l];
							if(canGo(nr,nc)) {
								tmp[nr][nc]+=t;
								tmp[j][k]-=t;
							}
						}			
					}
				}
			}
			for (int j = 0; j < n; j++) {
				map[j] = tmp[j].clone();
			}
			
			int top = air - 1;

			for (int j = top - 1; j > 0; j--) {
				map[j][0] = map[j - 1][0];
			}
			for (int j = 0; j < m - 1; j++) {
				map[0][j] = map[0][j + 1];
			}
			for (int j = 0; j < top; j++) {
				map[j][m - 1] = map[j + 1][m - 1];
			}
			for (int j = m - 1; j > 1; j--) {
				map[top][j] = map[top][j - 1];
			}

			map[top][1] = 0;

			int bot = air;

			for (int j = bot + 1; j < n - 1; j++) {
				map[j][0] = map[j + 1][0];
			}
			for (int j = 0; j < m - 1; j++) {
				map[n - 1][j] = map[n - 1][j + 1];
			}
			for (int j = n - 1; j > bot; j--) {
				map[j][m - 1] = map[j - 1][m - 1];
			}
			for (int j = m - 1; j > 1; j--) {
				map[bot][j] = map[bot][j - 1];
			}

			map[bot][1] = 0;
		}
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]>0) {
					sum+=map[i][j];
				}
			}
		}
		System.out.println(sum);
	}

	static boolean canGo(int r, int c) {
		if(0<=r && r<n && 0<=c && c<m && map[r][c]!=-1) {
			return true;
		}
		return false;
	}
}