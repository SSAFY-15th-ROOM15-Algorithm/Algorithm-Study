package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q18808 {

    static int[][] board;
    static int N, M, K;

    static class Sticker {
        int[][] coordinates;
        int R, C;

        public Sticker(int R, int C) {
            this.R = R;
            this.C = C;
            coordinates = new int[R][C];
        }

        public void rotate() {
            int[][] newOrdinates = new int[C][R];

            for (int i = 0; i < C; i++) {
                for (int j = 0; j < R; j++) {
                    newOrdinates[i][j] = coordinates[R - j - 1][i];
                }
            }

            coordinates = newOrdinates;
            int temp = R;
            R = C;
            C = temp;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        Sticker[] stickers = new Sticker[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Sticker sticker = new Sticker(r, c);
            for (int x = 0; x < r; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < c; y++) {
                    sticker.coordinates[x][y] = Integer.parseInt(st.nextToken());
                }
            }
            stickers[i] = sticker;
        }

        for (Sticker sticker : stickers) {
            patch(sticker);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    static void patch(Sticker sticker) {
        for (int k = 0; k < 4; k++) { // 4번 rotation 가능하므로.
            for (int i = 0; i <= N - sticker.R; i++) {
                for (int j = 0; j <= M - sticker.C; j++) {
                    if (attach(sticker, i, j)) {
                        reflect(sticker, i, j);
                        return;
                    }

                }
            }
            sticker.rotate();
        }
    }

    // board에 실제로 반영
    static void reflect(Sticker sticker, int x, int y) {
        for (int i = 0; i < sticker.R; i++) {
            for (int j = 0; j < sticker.C; j++) {
                if (sticker.coordinates[i][j] == 1) {
                    board[x + i][y + j] = 1;
                }
            }
        }
    }

    // x, y 를 좌측 상단으로 완전 탐색해서 해당 스티커를 온전히 붙일 수 있는지 검사
    static boolean attach(Sticker sticker, int x, int y) {
        for (int i = 0; i < sticker.R; i++) {
            for (int j = 0; j < sticker.C; j++) {
                if (sticker.coordinates[i][j] == 1) {
                    if (board[x + i][y + j] != 0) { // 붙일 수 없는게 확인되면 바로 false 리턴
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
