import java.util.Arrays;
import java.util.Scanner;

public class Q2667 {

    static int N;
    static int[][] arr;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static int dfs(int r, int c) {
    	visited[r][c] = true;
    	int count = 1;
    	
    	//4방향 탐색
    	for(int d=0; d<4; d++) {
    		int nr = r+dr[d];
    		int nc = c+dc[d];
    		
    		if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
    			if(arr[nr][nc] == 1 && !visited[nr][nc]) {
    				count += dfs(nr, nc);
    			}
    		}
    	}
    	return count;
    }
    

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            String str = sc.next();
            for(int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        int[] result = new int[N*N];
        int idx = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                if(arr[i][j] == 1 && !visited[i][j]) {
                    
                    result[idx] = dfs(i,j);
                    idx++;
                }
            }
        }

        int[] ans = new int[idx];

        for(int i=0; i<idx; i++) {
            ans[i] = result[i];
        }
        
        Arrays.sort(ans);
        
        //총 단지 수
        System.out.println(idx);
        
        //각 단지내 집의 수
        for(int i = 0; i < idx; i++) {
            System.out.println(ans[i]);
        }
    }
}