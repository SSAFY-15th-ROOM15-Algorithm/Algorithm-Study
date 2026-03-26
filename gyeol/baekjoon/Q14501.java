import java.util.*;

public class Q14501 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [][] schedule = new int[N][2];

        for (int i = 0; i < N; i++) {
            schedule[i][0] = sc.nextInt(); // 기간
            schedule[i][1] = sc.nextInt(); // 수익
        }

        int [] dp = new int[N + 1];

        for (int i = N - 1; i >= 0; i--) {
            int time = schedule[i][0];
            int pay = schedule[i][1];

            if (i + time <= N) {
                dp[i] = Math.max(dp[i + 1], pay + dp[i + time]);
            } else {
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[0]);
    }
}