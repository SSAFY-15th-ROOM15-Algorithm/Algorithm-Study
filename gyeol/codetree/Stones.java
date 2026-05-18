import java.util.*;

public class Stones {
	static int N, K, M, ans;
	static int [][] board;
	
	static ArrayList<int []> starts = new ArrayList<>();
	static ArrayList<int []> stones = new ArrayList<>();
	static ArrayList<int []> selected = new ArrayList<>();
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt(); // 시작점의 수
		M = sc.nextInt(); // 치워야 할 돌의 개수
		
		board = new int [N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt(); // 0 : 이동 O, 1 : 이동 X (돌)
				
				if(board[i][j] == 1) stones.add(new int [] {i, j});
			}
		}
		
		for(int k = 0; k < K; k++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			starts.add(new int [] {x, y});
		}
		
		ans = 0;
		combination(0, 0);
		System.out.println(ans);
	}

	private static void combination(int idx, int cnt) {
		if(cnt == M) {
			for(int [] s : selected) { // 돌 제거
				int x = s[0]; int y = s[1];
				board[x][y] = 0;
			}
			
			ans = Math.max(ans, bfs()); 
			
			for(int [] s : selected) { // 돌 복구
				int x = s[0]; int y = s[1];
				board[x][y] = 1;
			}
			return;
		}
		
		if(idx == stones.size()) return; // 종료 조건
		
		// 선택
		selected.add(stones.get(idx));
		combination(idx + 1, cnt + 1);
		
		// 선택 X
		selected.remove(selected.size() - 1);
		combination(idx + 1, cnt);
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean [][] visited = new boolean [N][N];
		
		for(int [] s : starts) {
			q.add(s);
			visited[s[0]][s[1]] = true;
		}
		
		int cnt = starts.size();
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(visited[nx][ny] || board[nx][ny] == 1) continue;
				
				visited[nx][ny] = true;
				q.add(new int [] {nx, ny});
				cnt++;
			}
		}
		return cnt;
	}
}
