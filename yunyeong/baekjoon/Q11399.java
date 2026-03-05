package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Q11399 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 입력 받아서 배열에 넣고
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		for (int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 앞사람 숫자는 계속 더해짐 -> 더 적게 걸리는 사람이 앞으로 와야함
		Arrays.sort(arr);
		
		int time = 0; // 각자 돈 뽑는 시간
		int result = 0;  // 총합
		for (int i=0; i<N; i++) {
			time += arr[i];
			result += time;
		}
		
		System.out.println(result);
	}
	
}
