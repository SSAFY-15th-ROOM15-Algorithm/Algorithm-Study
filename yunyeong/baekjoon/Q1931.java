package baekjoon;

import java.util.Scanner;
import java.util.Arrays;

public class Q1931 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] arr = new int[N][2];

		for (int i=0; i<N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}

		// 빨리 끝내고 다음꺼 또 해야 가장 많은 회의를 할 수 있음.
		// 끝나는 시간이 빠른 순(2순위로 시작시간 순)으로 정렬해두고 끝나는시간-시작시간-끝나는시간-... 하고 카운트하면 그게 최대 회의 수!
		
		// 람다식으로 정렬해보기
		Arrays.sort(arr, (o1, o2) -> { // arr를 정렬할건데
			if(o1[1] == o2[1]) return o1[0] - o2[0]; // 둘이 끝나는 시간이 같으면 시작하는 시간을 비교해서 작은게 위로 오게
			return o1[1] - o2[1];  // **중요** 끝나는시간을 기준으로 오름차순으로 정렬. [위 행 끝나는시간 - 아래 행 끝나는시간] 이 음수면 그대로 두고 양수면 자리를 바꿔라!
		});

		// 정렬 잘 됐나 확인 좀 해보고
//		for (int i=0; i<N; i++) {
//			for (int j=0; j<2; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		// 이제 끝나는시간 -> 시작하는 시간 찾아서 이어보깅.. greedy
		
		int count = 0;
		int startTime = 0;
		
		for (int i=0; i<N; i++) {
			if (arr[i][0] >= startTime) {
				startTime = arr[i][1];
				count++;
			}
		}

		System.out.println(count);
		
		
	}
}

