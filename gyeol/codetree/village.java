import java.util.*;

public class village {
	static int N, max;
	static int [][] board;
	static boolean [][] visited;
	static ArrayList<Integer> list;
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N][N];
		visited = new boolean[N][N];
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt(); // 1 : 사람, 0 : 벽
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == 1 && !visited[i][j]) {
					int cnt = dfs(i, j);
					list.add(cnt);
				}
			}
		}
		
		int ans = list.size();
		Collections.sort(list);
		
		System.out.println(ans);
		for(int i : list) {
			System.out.println(i);
		}
		
	}

	private static int dfs(int x, int y) {
		visited[x][y] = true;
		int cnt = 1;
		
		for(int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			if(board[nx][ny] == 0 || visited[nx][ny]) continue;
			
			cnt += dfs(nx, ny);
		}
		return cnt;
	}
}
