import java.util.*;

public class Q17471 {
    static int N, min = Integer.MAX_VALUE;
    static int[] people;
    static boolean[] selected;
    static ArrayList<Integer>[] gerry;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        people = new int[N + 1];
        selected = new boolean[N + 1];
        gerry = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
        	gerry[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            people[i] = sc.nextInt();
        }

        for (int i = 1; i <= N; i++) {
            int cnt = sc.nextInt();
            for (int j = 0; j < cnt; j++) {
                int next = sc.nextInt();
                gerry[i].add(next);
            }
        }

        combination(1);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

	private static void combination(int idx) { // 부분집합 (백트래킹)
		if(idx == N + 1) {
			check();
			return;
		}
		
		selected[idx] = true;
		combination(idx + 1);
		
		selected[idx] = false;
		combination(idx + 1);
	}

	private static void check() { // 구역 나누기
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			if(selected[i]) a.add(i);
			else b.add(i);
		}
		
		if(a.size() == 0 || b.size() == 0) return;
		
		// 구역끼리 연결 되어있는지
		if(bfs(a) && bfs(b)) {
			int diff = Math.abs(sum(a) - sum(b));
			min = Math.min(min, diff);
		}
	}

	private static boolean bfs(ArrayList<Integer> list) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(list.get(0));
		
		boolean[] connect = new boolean[N + 1];
		connect[list.get(0)] = true;
		
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : gerry[cur]) {
				if(!connect[next] && list.contains(next)) {
					connect[next] = true;
					q.add(next);
					cnt++;
				}
			}
		}
		
		return cnt == list.size();
	}
	
	private static int sum(ArrayList<Integer> list) {
		int s = 0;
		for(int i : list) {
			s += people[i];
		}
		return s;
	}
}