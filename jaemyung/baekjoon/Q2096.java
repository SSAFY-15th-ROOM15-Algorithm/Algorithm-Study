import java.util.*;

public class Q2096 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        int[][] arr = new int[N][3];
        int[][] dp1 = new int[N][3];
        int[][] dp2 = new int[N][3];

        for (int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }

        dp1[0][0] = arr[0][0];
        dp1[0][1] = arr[0][1];
        dp1[0][2] = arr[0][2];
        dp2[0][0] = arr[0][0];
        dp2[0][1] = arr[0][1];
        dp2[0][2] = arr[0][2];

        for (int i = 1; i < N; i++) {
            dp1[i][0] = Math.min(dp1[i - 1][0], dp1[i - 1][1]) + arr[i][0];
            
            dp1[i][1] = Math.min(Math.min(dp1[i - 1][0], dp1[i - 1][1]),dp1[i - 1][2]) + arr[i][1];
            
            dp1[i][2] = Math.min(dp1[i - 1][1], dp1[i - 1][2]) + arr[i][2];
        }

        for (int i = 1; i < N; i++) {
        	dp2[i][0] = Math.max(dp2[i - 1][0], dp2[i - 1][1]) + arr[i][0];
        	
        	dp2[i][1] = Math.max(Math.max(dp2[i - 1][0], dp2[i - 1][1]),dp2[i - 1][2]) + arr[i][1];
        	
        	dp2[i][2] = Math.max(dp2[i - 1][1], dp2[i - 1][2]) + arr[i][2];
        }

        int min = Math.min(dp1[N - 1][0], Math.min(dp1[N - 1][1], dp1[N - 1][2]));
        int max = Math.max(dp2[N - 1][0], Math.max(dp2[N - 1][1], dp2[N - 1][2]));
        System.out.println(max + " " + min);
    }
}