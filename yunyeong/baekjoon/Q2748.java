package baekjoon;

import java.util.Scanner;

public class Q2748 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
        
        // n이 90일 때 피보나치 수는 int 범위를 넘어가므로 long 배열 사용
        // n번째 인덱스까지 필요하므로 크기를 n + 1로 설정
        long[] dp = new long[n + 1];
        
        // 초기값
        dp[0] = 0;
        if (n >= 1) {
            dp[1] = 1;
        }
        
        // dp 레츠고
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        // 결과 출력
        System.out.println(dp[n]);
	}
}
