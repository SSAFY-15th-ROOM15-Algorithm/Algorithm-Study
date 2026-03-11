package practice;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Q2178 {
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void bfs(int r, int c) {
        Queue<Integer> qr = new ArrayDeque<>();
        Queue<Integer> qc = new ArrayDeque<>();

        qr.add(r);
        qc.add(c);
        
        visited[r][c] = true;

        while (!qr.isEmpty()) {
            int cr = qr.poll();
            int cc = qc.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (!visited[nr][nc] && arr[nr][nc] == 1) {
                        visited[nr][nc] = true;
                        arr[nr][nc] = arr[cr][cc] + 1;
                        qr.add(nr);
                        qc.add(nc);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);

        System.out.println(arr[N - 1][M - 1]);
    }
}
