import java.util.*;

public class Q2667 {
	static int n;
	static boolean[][] map;
	static boolean[][] visited;
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	static Queue<int[]> q = new ArrayDeque<>();
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new boolean[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String str = sc.next();
			for (int j = 0; j < n; j++) {
				if(str.charAt(j)=='1') {
					map[i][j] = true;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j] && !visited[i][j]) {
					visited[i][j] = true;
					q.offer(new int[] {i,j});
					bfs(i,j);
				}
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	static void bfs(int r, int c) {
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int curR = tmp[0];
			int curC = tmp[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = curR + dr[i];
				int nc = curC + dc[i];
				if(canGo(nr,nc)) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr,nc});
					cnt++;
				}
			}
		}
		list.add(cnt);
	}
	
	static boolean canGo(int r, int c) {
		if(0<=r && r<n && 0<=c && c<n && map[r][c] && !visited[r][c]) {
			return true;
		}
		return false;
	}
}