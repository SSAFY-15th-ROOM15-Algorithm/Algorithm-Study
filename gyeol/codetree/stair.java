import java.util.*;

public class stair {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int mod = 10007;
		
		int [] dp = new int[N + 1];
		
		dp[0] = 1;
		
		for(int i = 2; i <= N; i++) {
			if(i - 2 >= 0) {
				dp[i] += dp[i - 2];
			}
			if(i - 3 >= 0) {
				dp[i] += dp[i - 3];
			}
			dp[i] %= mod;
		}
		
		System.out.println(dp[N]);
	}
}
