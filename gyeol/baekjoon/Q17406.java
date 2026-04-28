import java.util.*;

public class Q17406 {
	static int N, M, K, ans;
	static int [][] board;
	static boolean [] visited;
	static Rotate[] order;
	static ArrayList<Rotate> ops;
	
	static class Rotate{
		int r, c, s;
		
		public Rotate(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		ans = Integer.MAX_VALUE;
		
		board = new int[N][M];
		visited = new boolean[K];
		order = new Rotate[K];
		ops = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < K; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			int s = sc.nextInt();
			
			ops.add(new Rotate(r, c, s));
		}
		dfs(0);
		System.out.println(ans);
	}
	
	private static void dfs(int depth) {
		if(depth == K) {
			int [][] copy = copyBoard(board);
			
			for(int i = 0; i < K; i++) {
				rotate(copy, order [i]);
			}
			int min = calc(copy);
			ans = Math.min(ans, min);
		}
		
		for(int i = 0; i < K; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			
			order[depth] = ops.get(i);
			
			dfs(depth + 1);
			visited[i] = false;
		}
		
	}

	private static void rotate (int [][] board, Rotate op) {
		int r = op.r; int c = op.c; int s = op.s;
		int x1 = r - s; int y1 = c - s; // board[1][2]
		int x2 = r + s; int y2 = c + s; // board[5][6]
		
		for(int layer = 0; layer < s; layer++) {
			
			int top = x1 + layer;
			int left = y1 + layer;
			int bottom = x2 - layer;
			int right = y2 - layer;
			
			// ↑
			int temp = board[top][left];
			for(int i = top; i < bottom; i++) {
				board[i][left] = board[i + 1][left];
			}
			// ←
			for(int i = left; i < right; i++) {
				board[bottom][i] = board[bottom][i + 1];
			}
			// ↓
			for(int i = bottom; i > top; i--) {
				board[i][right] = board[i - 1][right];
			}
			// →
			for(int i = right; i > left; i--) {
				board[top][i] = board[top][i - 1];
			}
			board[top][left + 1] = temp;
		}
	}
	
	private static int[][] copyBoard (int [][] board){
		int [][] copy = new int [N][M];
		
		for(int i = 0; i < N; i++) {
			copy[i] = board[i].clone();
		}
		
		return copy;
	}
	
	private static int calc(int [][] board) {
		int [] arr = new int [N];
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				arr[i] += board[i][j];
			}
		}
		
		for(int i : arr) {
			min = Math.min(min, i);
		}
		
		return min;
	}
}
