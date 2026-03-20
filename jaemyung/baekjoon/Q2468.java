import java.util.*;

public class Q2468 {
    static int n, max;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        grid = new int[n][n];
        int top = 0;
        max = 0;
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		grid[i][j] = sc.nextInt();
        		top = Math.max(top, grid[i][j]);
        	}        	
        }

        for (int i = 0; i < top; i++) {
        	visited = new boolean[n][n];   
        	int cnt = 0;
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if(grid[j][k]>i && !visited[j][k]) {
						dfs(j,k,i);		
						cnt++;
					}
				}
			}
			max = Math.max(max, cnt);
		}
        
        System.out.println(max);
    }
    
    static void dfs(int r, int c, int water) {
    	for (int i = 0; i < 4; i++) {
    		int nr = r + dr[i];
    		int nc = c + dc[i];
			if(canGo(nr, nc, water)) {
				visited[nr][nc] = true;
				dfs(nr,nc,water);
			}
		}
    }
    
    static boolean canGo(int r, int c, int water) {
    	if(0<=r && r<n && 0<=c && c<n && !visited[r][c] && grid[r][c]>water) {
    		return true;
    	}
    	return false;
    }
}