package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Q9663 {

    private static int N;
    private static int answer = 0;
    private static boolean[] cols;
    private static boolean[] posDiag;
    private static boolean[] negDiag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cols = new boolean[N];
        posDiag = new boolean[2 * N];
        negDiag = new boolean[2 * N];

        backtrack(0);
        System.out.println(answer);
    }

    // n번째 row에 queen을 배치
    private static void backtrack(int row) {

        if (row == N) {
            answer++;
            return;
        }

        for (int c = 0; c < N; c++) {
            int pIdx = row + c;
            // nIdx의 경우 (N - 1) 만큼 offset
            int nIdx = row - c + (N - 1);

            if (!cols[c] && !posDiag[pIdx] && !negDiag[nIdx]) {
                cols[c] = posDiag[pIdx] = negDiag[nIdx] = true;
                backtrack(row + 1);
                cols[c] = posDiag[pIdx] = negDiag[nIdx] = false;
            }
        }
    }
}
