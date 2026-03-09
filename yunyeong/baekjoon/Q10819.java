package baekjoon;

import java.util.Scanner;

public class Q10819 {
	
	static int N;
	static int[] input; // 입력받은 순서로 넣을 배열
	static int[] ans;  // 최대 차이가 날 수 있는 순서대로 정리해서 이걸로 계산
	static boolean[] visited;  // 몇번째꺼 뽑았어! 표시해둘 거
	static int maxResult = 0;  // 결과값
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		input = new int[N];
		ans = new int[N];
		visited = new boolean[N];
		
		
		 for (int i=0; i<N; i++) {
			 input[i] = sc.nextInt();
		 }
		 
		 backtrack(0);  // 재귀함수로 순열 구해서 계산하깅
		 
		 System.out.println(maxResult);
	}
	
	static void backtrack(int num) {
		if (num == N) {  // N개 다 뽑았으면 차이의 합 계산하는 것임
			int sum = 0;
			for (int i=0; i<N-1; i++) {
				sum += Math.abs(ans[i] - ans[i+1]);
			}
			if (sum > maxResult) maxResult = sum;  // 이번 순열로 만든 차이의 합이 젤 크면 갱신
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (!visited[i]) {
				
				visited[i] = true;    // i번째꺼 썼어! 표시
				ans[num] = input[i];  // ans 배열에 i번째 숫자 넣기
				backtrack(num+1);     // 다음 자리에 올거 찾으러 감 
				
				visited[i] = false;   // 그렇게 순열 하나 만들고 나면 다시 안 썼다고 표시해서 다음 순열 만들기
			}
		}
	}
}
