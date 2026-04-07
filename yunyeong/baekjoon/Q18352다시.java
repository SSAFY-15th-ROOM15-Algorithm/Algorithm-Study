package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// visited 말고 dist로 다시 풀기
public class Q18352다시 {
	
	static List<Integer>[] adj; // 정점 연결 정보 넣을 배열
	static int[] dist; // 시작점으로부터의 거리 정보를 넣을 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		dist = new int[N+1];
		
		// 배열 초기화
		for (int i=1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		Arrays.fill(dist, -1);  // 초깃값 -1로 채워둠. 방문 안했다는 뜻
		
		// 그래프 연결 정보 넣기
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
		}
		
		bfs(X);
		
		List<Integer> answer = new ArrayList<>(); // 최종 정답인 정점만 넣을 배열
		
		for (int i=1; i<=N; i++) {
			if (dist[i] == K) {  // 시작점으로부터 거리가 K인 애들만
				answer.add(i);   // answer에 넣어줌
			}
		}
		if (!answer.isEmpty()) {
			for (int i=0; i<answer.size(); i++) {
				System.out.println(answer.get(i));
			}
		} else System.out.println(-1);  // 비어있으면 -1 출력
		
	}
	
	
	static void bfs(int x) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(x);
		dist[x] = 0;  // 시작점은 거리 0
		
		while (!q.isEmpty()) {
			int cur = q.poll();  // 현재꺼 뽑아봄
			
			for (int next : adj[cur]) { // 이어진 곳이 있다면
				if (dist[next] == -1) { // 방문 안했던 곳이면
					dist[next] = dist[cur] + 1;  // 거리는 아까보다 +1
					q.offer(next); // 큐에 넣음
				}
			}
		}
	}
}
