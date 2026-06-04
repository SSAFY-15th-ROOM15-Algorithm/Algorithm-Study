import java.util.*;

public class Maximum_Minimum_Path {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int [][] arr = new int [N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        int left = 1;
        int right = 1000000;
        int ans = 0;

        while(left <= right){
            int mid = (left + right) / 2;

            boolean [][] dp = new boolean[N][N];
            if(arr[0][0] >= mid){
                dp[0][0] = true;
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(arr[i][j] < mid) continue;
                    if(i > 0 && dp[i - 1][j]){
                        dp[i][j] = true;
                    }
                    if(j > 0 && dp[i][j - 1]){
                        dp[i][j] = true;
                    }
                }
            }

            if(dp[N - 1][N - 1]){
                ans = mid;
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }
}
