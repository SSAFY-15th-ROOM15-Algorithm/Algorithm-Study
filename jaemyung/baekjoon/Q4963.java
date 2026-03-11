import java.util.*;

public class Q4963 {
	static int n,m,cnt;
	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	static boolean[][] map;
	static boolean[][] visited;
 	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            m = sc.nextInt();
            n = sc.nextInt();
            if (m == 0 && n == 0) break;

            map = new boolean[n][m];
            visited = new boolean[n][m];
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int a = sc.nextInt();
                    if(a == 1) {
                    	map[i][j] = true;
                    }
                }
            }
            
            cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] && !visited[i][j]) {
                        cnt++;
                        dfs(i, j);
                    }
                }
            }
            System.out.println(cnt);        
        }
    }
    
    static void dfs(int r, int c) {
    	visited[r][c] = true;
    	
    	for (int i = 0; i < 8; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];

			if(canGo(nr,nc)) {
				dfs(nr,nc);
			}
		}
    }
    
    static boolean canGo(int r, int c) {
    	if(0<=r && r<n && 0<=c && c<m && map[r][c] && !visited[r][c]) {
    		return true;
    	}
    	return false;
    }
}