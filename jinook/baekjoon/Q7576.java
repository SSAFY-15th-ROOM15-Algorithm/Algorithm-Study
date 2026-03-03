package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 백준 토마토
// [bfs 기초] 시작점이 여러곳인 경우, 각각의 시작점을 기준으로 뻗어나가도록 구현하는 문제
public class Q7576 {
    private static int[][] grid;
    private static int N, M;
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        List<int[]> starts = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 1) {
                    // 초기에 익어있는 토마토 위치 삽입
                    starts.add(new int[]{i, j});
                }
                grid[i][j] = input;
            }
        }

        System.out.println(bfs(starts));
    }

    private static int bfs(List<int[]> starts) {
        Queue<int[]> q = new ArrayDeque<>(starts);
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if (canGo(nx, ny)) {
                    // 인접한 토마토라면 방문처리(현재 날짜 +1) 후 queue에 삽입
                    grid[nx][ny] = grid[now[0]][now[1]] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        int ans = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 익지 않은 토마토가 있다면
                if (grid[i][j] == 0) {
                    return -1;
                }
                //
                ans = Math.max(ans, grid[i][j]);
            }
        }

        return ans - 1;
    }

    private static boolean canGo(int x, int y) {
        // 격자 내에 있고, 토마토라면
        return x >= 0 && y >= 0 && x < N && y < M && grid[x][y] == 0;
    }
}
