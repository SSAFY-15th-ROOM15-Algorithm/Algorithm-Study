import java.util.*;

public class Q17070 {
	static int n,cnt;
	static int[][] dr = {{0,1},{1,1},{0,1,1}}; // 가로 0, 세로 1, 대각선 2
	static int[][] dc = {{1,1},{0,1},{1,0,1}};
	static int[][] nextDir = {{0,2},{1,2},{0,1,2}};
	static boolean[][] grid;
 	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int a = sc.nextInt();
				if(a==1) {
					grid[i][j] = true;
				}
			}
		}
        
        grid[0][1] = true;
        dfs(0,1,0);
        System.out.println(cnt);
    }
    
    static void dfs(int r, int c, int dir) {
    	if(r==n-1 && c==n-1) {
    		cnt++;
    		return;
    	}
    	for (int i = 0; i < dr[dir].length; i++) {
			int nr = r+dr[dir][i];
			int nc = c+dc[dir][i];
			int nd = nextDir[dir][i];

			if(canGo(nr,nc)) {
				if(nd==2) {
					if (grid[r + 1][c] || grid[r][c + 1]) {
						continue;
					}
				}
				dfs(nr,nc,nd);
			}
		}
    }
    
    static boolean canGo(int r, int c) {
    	if(0<=r && r<n && 0<=c && c<n && !grid[r][c]) {
    		return true;
    	}
    	return false;
    }
}