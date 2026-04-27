import java.util.*;

public class Q2383 {
	static int N, ans;
	static int [][] board;
	static boolean [] selected;
	static ArrayList<stair> stairs;  // 계단 정보
	static ArrayList<int []> people; // 사람 정보
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	static class stair {
		int x, y, k;
		
		public stair(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
	
	// 모든 사람들이 계단을 내려가 이동이 완료되는 시간이 최소가 되는 경우 최소 시간 출력
	// 1. 계단 입구까지 이동 시간
	//    dist = Math.abs(px - sx) + Math.abs(py - sy);
	// 2. 계단을 내려가는 시간
	//    계단 도착 + 1 + K (단 3명까지만)

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			board = new int [N][N];
			
			stairs = new ArrayList<>();
            people = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					board[i][j] = sc.nextInt(); // 1 : 사람 , 2 이상 : 계단 입구
					
					if(board[i][j] == 1) people.add(new int [] {i, j});
					else if(board[i][j] > 1) stairs.add(new stair(i, j, board[i][j]));
				}
			}
			
			selected = new boolean[people.size()];
			ans = Integer.MAX_VALUE;
			
			combination(0);
			
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void combination(int idx) {
		if(idx == people.size()) {
			solve();
			return;
		}
		
		selected[idx] = true;
		combination(idx + 1);
		
		selected[idx] = false;
		combination(idx + 1);
	}

	private static void solve() {
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		
		for(int i = 0; i < people.size(); i++) {
			if(selected[i]) a.add(i);
			else b.add(i);
		}
		
		// 계단 입구까지 거리
		ArrayList<Integer> timeA = move(a, 0);
		ArrayList<Integer> timeB = move(b, 1);
		
		Collections.sort(timeA);
		Collections.sort(timeB);
		
		// 계단 도착 후 내려가는 시간 -> 제일 마지막 도착시간
		int endA = down(timeA, stairs.get(0).k);
		int endB = down(timeB, stairs.get(1).k);
		
		int max = Math.max(endA, endB);
		ans = Math.min(ans, max);
		
	}

	private static ArrayList<Integer> move(ArrayList<Integer> list, int stairIdx) {
		ArrayList<Integer> times = new ArrayList<>();
		
		int sx = stairs.get(stairIdx).x;
		int sy = stairs.get(stairIdx).y;
		
		for(int i = 0; i < list.size(); i++) {
			int [] cur = people.get(list.get(i));
			int px = cur[0];
			int py = cur[1];
			
			int dist = Math.abs(px - sx) + Math.abs(py - sy);
			times.add(dist + 1);
		}
		return times;
	}
	
	private static int down(ArrayList<Integer> times, int k) {
		Queue<Integer> q = new ArrayDeque<>();
		for(int t : times) {
			while(!q.isEmpty() && q.peek() <= t) q.poll();
			
			if(q.size() < 3) q.offer(t + k);
			else {
				int next = q.poll();
				q.offer(next + k);
			}
		}
		
		int max = 0;
		while(!q.isEmpty()) max = Math.max(max, q.poll());
		
		return max;
	}
	
}
