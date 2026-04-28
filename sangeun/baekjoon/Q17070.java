import java.util.Scanner;

public class Q17070 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] house = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                house[i][j] = sc.nextInt();
            }
        }

        int[][][] dp = new int[N][N][3];
        // 0: 가로, 1: 세로, 2: 대각선

        // 시작 상태: (0,1) 끝점, 가로
        dp[0][1][0] = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 현재 칸이 벽이면 진행 불가
                if (house[r][c] == 1) continue;

                // 1) 가로 상태 -> 오른쪽
                if (c + 1 < N && house[r][c + 1] == 0) {
                    dp[r][c + 1][0] += dp[r][c][0];
                }

                // 2) 세로 상태 -> 아래
                if (r + 1 < N && house[r + 1][c] == 0) {
                    dp[r + 1][c][1] += dp[r][c][1];
                }

                // 3) 대각선 상태 -> 오른쪽
                if (c + 1 < N && house[r][c + 1] == 0) {
                    dp[r][c + 1][0] += dp[r][c][2];
                }

                // 4) 대각선 상태 -> 아래
                if (r + 1 < N && house[r + 1][c] == 0) {
                    dp[r + 1][c][1] += dp[r][c][2];
                }

                // 5) 가로/세로/대각선 모두 -> 대각선
                if (r + 1 < N && c + 1 < N
                        && house[r][c + 1] == 0
                        && house[r + 1][c] == 0
                        && house[r + 1][c + 1] == 0) {
                    dp[r + 1][c + 1][2] += dp[r][c][0];
                    dp[r + 1][c + 1][2] += dp[r][c][1];
                    dp[r + 1][c + 1][2] += dp[r][c][2];
                }
            }
        }

        int answer = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
        System.out.println(answer);
    }
}