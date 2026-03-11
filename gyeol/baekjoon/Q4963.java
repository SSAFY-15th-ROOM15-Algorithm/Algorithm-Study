import java.util.Scanner;

public class Q4963 {
    static int W, H;
    static int [][] map;
    static boolean [][] visited;
    
    static int [] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int [] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
        	W = sc.nextInt();
        	H = sc.nextInt();
        	
        	if(W == 0 && H == 0) return;
        	
        	map = new int[H][W];
        	for(int i = 0; i < H; i++) {
        		for(int j = 0; j < W; j++) {
        			map[i][j] = sc.nextInt();
        		}
        	}
        	
        	visited = new boolean [H][W];
        	int cnt = 0;
        	for(int i = 0; i < H; i++) {
        		for(int j = 0; j < W; j++) {
        			if(!visited[i][j] && map[i][j] == 1) {
        				dfs(i, j);
        				cnt++;
        			}
        		}
        	}
        	
        	System.out.println(cnt);
        }
    }

	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int dir = 0; dir < 8; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
			if(visited[nx][ny] || map[nx][ny] != 1) continue;
			
			dfs(nx, ny);
		}
	}
}
