import java.util.*;

public class BST {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int [] dp = new int [N + 1];
		
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i = 2; i <= N; i++) {
			for(int root = 1; root <= i; root++) {
				dp[i] += dp[root - 1] * dp[i - root]; 
			}
		}
		System.out.println(dp[N]);
	}
}
