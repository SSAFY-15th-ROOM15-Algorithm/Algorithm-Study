import java.util.*;
public class Q1463 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int dp[] = new int[X + 1];

        for(int i = 2; i <= X; i++){
            dp[i] = dp[i - 1] + 1;

            if(X % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if(X % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }
        System.out.println(dp[X]);
    }
}