import java.util.*;

public class Q2193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        long[][] dp = new long[N + 1][2];
        
        dp[1][0] = 0; 
        dp[1][1] = 1;
        
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1]; // 길이가 i이고 0으로 끝나는 이친수 개수
            dp[i][1] = dp[i - 1][0]; // 길이가 i이고 1로 끝나는 이친수 개수
        }
        
        System.out.println(dp[N][0] + dp[N][1]);
    }
}