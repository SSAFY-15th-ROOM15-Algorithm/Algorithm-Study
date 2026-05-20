import java.util.*;

public class Rain {

    static int N, H, M;
    static int[][] board;
    static int[][] dist;
    static int[][] ans;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Queue<int[]> q = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        H = sc.nextInt();
        M = sc.nextInt();

        board = new int[N][N];
        dist = new int[N][N];
        ans = new int[N][N];

        // dist 배열 -1로 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();

                // 비를 피할 수 있는 공간(3)을 모두 큐에 넣기
                if (board[i][j] == 3) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        bfs();

        // 정답 배열 만들기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (board[i][j] == 2) ans[i][j] = dist[i][j]; // 사람 위치라면 최단거리 출력
                else ans[i][j] = 0; // 나머지는 0
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs() {

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                // 벽이면 이동 불가, 이미 방문한 곳
                if (board[nx][ny] == 1 || dist[nx][ny] != -1) continue;

                // 거리 갱신
                dist[nx][ny] = dist[x][y] + 1;

                q.offer(new int[]{nx, ny});
            }
        }
    }
}