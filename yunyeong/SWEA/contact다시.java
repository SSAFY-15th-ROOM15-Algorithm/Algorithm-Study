package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class contact다시 {
	
	static List<Integer>[] adj;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t=1; t<=10; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 받을 데이터의 길이
			int len = Integer.parseInt(st.nextToken());
			// 시작정점
			int start = Integer.parseInt(st.nextToken());
			
			// 배열 초기화
			adj = new ArrayList[101];
			// (정점 1부터 있으니까, 1부터 쓸거임)
			for (int i=1; i<=100; i++) {
				adj[i] = new ArrayList<>();				
			}
			
			// 방문여부 배열
			visited = new boolean[101];
			
			// 다음줄 입력 받기
			st = new StringTokenizer(br.readLine());
			
			// 입력처리 (from, to 번갈아 들어옴)
			for (int i=0; i<len/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj[from].add(to);
			}
			
			//bfs 함수
			int result = bfs(start);
			
			System.out.println("#"+t+" "+result);
		}	
	}
	
	static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		// 큐에 넣기
		q.offer(start);
		// 방문처리
		visited[start] = true;
		
		// 초깃값 - 시작 정점(얘랑 연결된 애가 없으면 얘가 바로 정답)
		int max = start;
		
		while (!q.isEmpty()) {
			int size = q.size();  // 이번 레벨의 사이즈(한번에 이만큼씩 돌고 max를 찾아야함)
			max = 0;  // 초기화 - 매 레벨마다 새 최대값을 구해야함
			
			for (int i=0; i<size; i++) {
				
				int cur = q.poll(); // 이번 레벨에 있는 애들을 하나씩 꺼내서 볼거임
				max = Math.max(max, cur); // 그중에 젤 높은 숫자인 애가 max
				
				for (int next : adj[cur]) { // 근데 더 이어진 줄이 있으면??
					if (!visited[next]) { // 걔가 전화 안 받았던 친구면 가야지
						visited[next] = true; // 방문처리해주고
						q.offer(next); // 큐에 넣어놓음. 다음 레벨에서 또 같은 레벨끼리 처리하고 max찾을거임
					}
				}
			}
		}
		
		return max;		
		
	}
}
