import java.util.*;

public class Q1987 {
    static int r, c, max;
    static char[][] board;
    static boolean[] visited = new boolean[26];
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        board = new char[r][c];
        max = 0;
        for (int i = 0; i < r; i++) {
			String str = sc.next();
			board[i] = str.toCharArray();
		}
        
        visited[board[0][0] - 'A'] = true;
        moved(0, 0, 1);
        
        System.out.println(max);
    }
    
    static void moved(int row, int col, int cnt) {
    	max = Math.max(max, cnt);

    	for (int i = 0; i < 4; i++) {
			int nr = row+dr[i];
			int nc = col+dc[i];
			
			if(canGo(nr, nc)) {
				visited[board[nr][nc]-'A'] = true;
				moved(nr,nc,cnt+1);
				visited[board[nr][nc]-'A'] = false;
			}
    	}
    }
    
    static boolean canGo(int row, int col) {
    	if(0<=row && row<r && 0<=col && col<c && !visited[board[row][col]-'A']) {
    		return true;
    	}
    	return false;
    }
}