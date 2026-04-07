package baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Q18352 {
	
	static List<Integer>[] adj;
	static boolean[] visited;
	static List<Integer> answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();  // 특정 거리
		int X = sc.nextInt();  // 출발 도시
		
		visited = new boolean[N+1];
		adj = new ArrayList[N+1];
		
		// 배열 초기화
		for (int i=1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		// 그래프 연결 정보 넣기
		for (int i=0; i<M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adj[from].add(to);
		}
		
		// bfs함수
		answer = new ArrayList<>();
		bfs(X, K);
		Collections.sort(answer);
		// 비어있으면 -1 출력하게
		if (answer.isEmpty()) answer.add(-1);
		
		// 도시 모두 출력
		for (int i=0; i<answer.size(); i++) {
			System.out.println(answer.get(i));
		}
	}
	
	static void bfs(int x, int k) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(x);
		visited[x] = true;
		
		int level = 0;  // 현재 레벨
		
		while (level <= k) { // 거리 k까지만
			// K까지 못 움직였는데 더 이상 갈 데가 없으면 다 지움
						
			int size = q.size(); // 이번 레벨에서 볼 애들 개수
			
			// 같은 레벨(같은 거리) 만큼만 큐에서 뽑아봄
			for (int i=0; i<size; i++) {
				int cur = q.poll();
				visited[cur] = true;
				if (level == k) answer.add(cur);  // 갈 수 있는 도시 배열에 넣기
				else {
					// 다음 갈 데가 방문 안했으면 큐에 넣어놓고 다음 레벨에서 가봄
					for (int next : adj[cur]) {
						if (!visited[next]) {
							q.offer(next);
							visited[next] = true;
						}
					}
				}
			}
			level++;  // 거리 +1
		}
		
	}
	
}
