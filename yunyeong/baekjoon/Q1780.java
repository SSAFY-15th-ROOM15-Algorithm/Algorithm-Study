package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1780 {
	
	static int[][] board;
    static int minusOne = 0; // -1로만 채워진 종이 수
    static int zero = 0;     // 0으로만 채워진 종이 수
    static int plusOne = 0;  // 1로만 채워진 종이 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0, 0, N);

        System.out.println(minusOne);
        System.out.println(zero);
        System.out.println(plusOne);
    }

    // 종이를 나누는 함수
    static void partition(int row, int col, int size) {
        // 1. 현재 영역이 모두 같은 숫자인지 체크
        if (checkColor(row, col, size)) {
            int val = board[row][col];
            if (val == -1) minusOne++;
            else if (val == 0) zero++;
            else plusOne++;
            return;
        }

        // 2. 숫자가 다르다면 9등분으로 쪼개서 봄
        int newSize = size / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                partition(row + i * newSize, col + j * newSize, newSize);
            }
        }
    }

    // 영역 내 숫자가 모두 같은지 확인하는 함수
    static boolean checkColor(int row, int col, int size) {
        int firstValue = board[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (board[i][j] != firstValue) {
                    return false;
                }
            }
        }
        return true;
    }
}