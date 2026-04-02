import java.util.*;

public class Q2747 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int [] dp = new int[46];
        
        dp[0] = 0; dp[1] = 1;
        if(n == 0 || n == 1) {
            System.out.println(dp[n]);
            return;
        }
        
        for(int i = 2; i <= n; i++) {
        	dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
        
    }
}