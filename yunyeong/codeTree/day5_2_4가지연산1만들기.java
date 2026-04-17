package codeTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
정수 N이 주어졌을 때, 다음 4가지 연산을 적절히 사용하여 연산의 횟수를 최소화 하여 숫자 1을 만들어 내려고 합니다.

 - 현재 수에서 1을 뺍니다.
 - 현재 수에 1을 더합니다.
 - 현재 수가 2로 나누어 떨어질 경우, 현재 수를 2로 나눕니다.
 - 현재 수가 3으로 나누어 떨어질 경우, 현재 수를 3으로 나눕니다.

예를 들어 수 11에서 시작하여 수 1을 만들어 내기 위해서는 최소 4번의 연산이 필요합니다.
1을 만들기 위해 필요한 최소 연산 횟수를 구하는 프로그램을 작성해보세요.
 */

public class day5_2_4가지연산1만들기 {

	static int[] dist = new int[1000002];
	static int N;
	static int result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		Arrays.fill(dist, -1);
		
		result = -1;
        bfs();
        
        System.out.println(result);

	}
	
	 
	public static void bfs() {
		
		Queue<Integer> queue = new LinkedList<>();
		
		// 시작점 넣기
		queue.add(N);
		dist[N] = 0;    // 시작점은 거리가 0임
		
		while (!queue.isEmpty()) {
			
//			System.out.println("q: " + queue);
			int curr = queue.poll();
			
//			System.out.println("curr: " + curr);
//			System.out.println("dist: " + dist[curr]);
//			System.out.println("------------------------------");
			
			// 움직일 수 있는 4가지 연산 탐색
			int[] next = new int[4];
			next[0] = curr - 1;
			next[1] = curr + 1;
			next[2] = (curr % 2 == 0) ? (curr / 2) : -2;
			next[3] = (curr % 3 == 0) ? (curr / 3) : -2;
			
			for (int i=0; i<4; i++) {
				// 여긴 목적지인감?
				if (curr == 1) {
					result = dist[curr];
					return; //맞으면 여기까지 걸린 거리 반환
				}

				if (next[i] > 0 && dist[next[i]] == -1 && next[i] <= 1000000) {
					// 나누어 떨어지지 않는 애들 안됨
					// N이랑 같은 값으로 돌아갈 필요 없음
					// 이미 큐 안에 있는 값이면 안 넣음 -> 더 먼걸로 다시 dist 갱신되면 안됨
					queue.add(next[i]);
					dist[next[i]] = dist[curr] + 1;
				}
			}
		}

	}	
}