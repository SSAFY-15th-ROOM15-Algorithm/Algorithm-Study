import java.util.*;

public class Q5656{
	static int N, W, H, ans;
	static int [][] board;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 구슬 수
			W = sc.nextInt(); H = sc.nextInt(); // H : 행, W : 열
			
			board = new int [H][W];
			
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					board[i][j] = sc.nextInt();
				}
			}
			
			ans = Integer.MAX_VALUE;
			solve(0, board);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	// 백트래킹
	private static void solve(int turn, int[][] board) {
		
		if(cntBlock(board) == 0) { // 가지치기
			ans = 0;
			return;
		}
		
		if(turn == N) {
			ans = Math.min(ans, cntBlock(board));
			return;
		}
		
		for(int col = 0; col < W; col++) { // 모든 열 선택
			int row = topRow(board, col);
			if(row == -1) continue;        // 빈 열
			
			int [][] copy = copyBoard(board);
			
			boom(copy, row, col);  // 폭발
			drop(copy);            // 중력
			solve(turn + 1, copy); // 다음 턴
		}
	}
	
	// BFS 폭발
	private static void boom(int[][] board, int row, int col) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int [] {row, col, board[row][col]});
		
		board[row][col] = 0;
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int range = cur[2];
			
			for(int dir = 0; dir < 4; dir++) {
				for(int k = 1; k < range; k++) {
					int nx = x + dx[dir] * k;
					int ny = y + dy[dir] * k;
					
					if(nx < 0 || ny < 0 || nx >= H || ny >= W || board[nx][ny] == 0) continue;
					q.offer(new int [] {nx, ny, board[nx][ny]});
					board[nx][ny] = 0;
				}
			}
		}
	}

    // 중력 처리 (temp 배열)
	private static void drop(int[][] board) {
        for (int col = 0; col < W; col++) {
            int[] temp = new int[H];
            int idx = H - 1;

            for (int row = H - 1; row >= 0; row--) {
                if (board[row][col] != 0) {
                    temp[idx--] = board[row][col];
                }
            }

            for (int row = 0; row < H; row++) {
                board[row][col] = temp[row];
            }
        }
    }

	// 가장 위 벽돌 찾기
	private static int topRow(int[][] board, int col) {
		for(int i = 0; i < H; i++) {
			if(board[i][col] != 0) return i;
		}
		return -1;
	}

	// 벽돌 수 카운팅
	private static int cntBlock(int [][] board) {
		int cnt = 0;
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(board[i][j] != 0) cnt++;
			}                       
		}
		return cnt;
	}
	
	// 보드 복사
	private static int[][] copyBoard (int[][] board){
		int [][] copy = new int[H][W];
		for(int i = 0; i < H; i++)  copy[i] = board[i].clone();
		
		return copy;
	}
}