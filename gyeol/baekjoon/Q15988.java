import java.util.*;

public class Q15988 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		int mod = 1000000009;
		int max = 1000000;
		long [] dp = new long [max + 1];
		
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3; i <= max; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % mod;
		}
		
		
		StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
        	int n = sc.nextInt();
            sb.append(dp[n]).append("\n");
        }
        
        System.out.print(sb);
	}
}
