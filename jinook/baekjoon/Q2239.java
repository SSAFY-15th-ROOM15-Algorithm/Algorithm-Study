package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q2239 {

    private static final int SIZE = 9;
    private static final int[][] board = new int[SIZE][SIZE];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < SIZE; i++) {
            String input = br.readLine();
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        solve();

        for (int[] row : board) {
            for (int n : row) {
                sb.append(n);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static boolean solve() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(r, c, num)) {
                            board[r][c] = num;

                            if (solve()) {
                                return true;
                            }

                            // 각 branch에서 실패한 경우 해당 칸을 다시 비워놔야 올바른 해를 구할 수 있음
                            board[r][c] = 0;
                        }
                    }
                    return false;
                }
            }
        }

        // 보드를 모두 채웠으면
        return true;
    }

    // row, col, box 에 대한 유효성 검사
    private static boolean isValid(int row, int col, int num) {
        // row 검사
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // column 검사
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // box 검사
        // ex) 2,3 검사라면? row : 0 ~ 2, col : 3 ~ 5 검사, 즉 2번째 박스를 검사할 수 있음!
        int localBoxRow = row - row % 3; // 0, 3, 6
        int localBoxColumn = col - col % 3; // 0, 3, 6
        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
