import java.util.*;

public class Q1003 { // 메모이제이션
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int [][] dp = new int[41][2];

        dp[0][0] = 1; dp[0][1] = 0;
        dp[1][0] = 0; dp[1][1] = 1;

        for(int i = 2; i < 41; i++){
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }

        for(int t = 0; t < T; t++){
            int N = sc.nextInt();
            System.out.println(dp[N][0] + " " + dp[N][1]);
        }
    }
}

/* 시간 초과 나는 이유 : 완전 재귀 → 중복 계산 폭발
public class Q1003 {
    static int N, zero, one;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 0; t < T; t++) {
            int N = sc.nextInt();

            zero = 0;
            one = 0;

            fibonacci(N);
            System.out.println(zero + " " + one);
        }
    }

    private static int fibonacci(int n) {
        if(n == 0) {
            zero++;
            return 0;
        }
        else if(n == 1){
            one++;
            return 1;
        }
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
 */