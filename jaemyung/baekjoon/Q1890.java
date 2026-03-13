import java.util.*;

public class Q1890 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        long[][] dp = new long[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int jump = map[i][j];
                
                if (dp[i][j] == 0 || (i == n - 1 && j == n - 1)) continue;

                int nr = i + jump;
                if (nr < n) {
                    dp[nr][j] += dp[i][j];
                }
                int nc = j + jump;
                if (nc < n) {
                    dp[i][nc] += dp[i][j];
                }
            }
        }
        
        System.out.println(dp[n - 1][n - 1]);
    }
}