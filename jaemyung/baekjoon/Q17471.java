package asd;

import java.util.*;

public class Q17471 {
	static int N, min = Integer.MAX_VALUE;
	static int[] popul;
	static List<Integer>[] adj;
	static boolean[] selected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		popul = new int[N + 1];
		adj = new ArrayList[N + 1];
		selected = new boolean[N + 1];

		for (int i = 1; i <= N; i++)
			popul[i] = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
			int count = sc.nextInt();
			for (int j = 0; j < count; j++)
				adj[i].add(sc.nextInt());
		}

		subset(1);

		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void subset(int index) {
		if (index == N + 1) {
			check();
			return;
		}

		selected[index] = true;
		subset(index + 1);
		selected[index] = false;
		subset(index + 1);
	}

	static void check() {
		List<Integer> groupA = new ArrayList<>();
		List<Integer> groupB = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (selected[i])
				groupA.add(i);
			else
				groupB.add(i);
		}

		if (groupA.isEmpty() || groupB.isEmpty())
			return;

		if (isConnected(groupA) && isConnected(groupB)) {
			int sumA = 0, sumB = 0;
			for (int i : groupA)
				sumA += popul[i];
			for (int i : groupB)
				sumB += popul[i];
			min = Math.min(min, Math.abs(sumA - sumB));
		}
	}

	static boolean isConnected(List<Integer> group) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		q.offer(group.get(0));
		visited[group.get(0)] = true;

		int count = 1;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int neighbor : adj[cur]) {
				if (group.contains(neighbor) && !visited[neighbor]) {
					visited[neighbor] = true;
					q.offer(neighbor);
					count++;
				}
			}
		}
		return count == group.size();
	}
}