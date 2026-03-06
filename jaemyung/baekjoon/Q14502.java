import java.util.*;

public class Q14502 {
    static int n, m;
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 2) {
					list.add(new int[] {i,j});
				}
			}
		}
        max = 0;
        
        buildWall(0,0,0);
        
        System.out.println(max);
    }
    
    static void buildWall(int r, int c, int depth) {
    	if(depth==3) {
    		int[][] tmp = new int[n][m];
    		int cnt = 0;
    		for (int i = 0; i < n; i++) {
    			tmp[i] = map[i].clone();
    		}
    		for (int i = 0; i < list.size(); i++) {
				spread(list.get(i)[0],list.get(i)[1],tmp);
			}
    		for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(tmp[i][j]==0) {
						cnt++;
					}
				}
			}
    		max = Math.max(max, cnt);
    		return;
    	}
    	
    	for (int i = r; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == 0) {
					map[i][j]=1;
					buildWall(i,j,depth+1);
					map[i][j]=0;
				}
			}
		}
    }
    
    static void spread(int r, int c, int[][] grid) {
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(new int[] {r,c});
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int curR = cur[0];
    		int curC = cur[1];
    		for (int i = 0; i < 4; i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];
				
				if(0<=nr && nr<n && 0<=nc && nc<m && grid[nr][nc]==0) {
					grid[nr][nc]=2;
					q.offer(new int[] {nr,nc});
				}
			}
    		
    	}
    }
}