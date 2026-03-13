package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1987 {
    private static int[][] grid;
    private static int R, C;
    private static final boolean[] visited = new boolean[26];
    private static int ans = 0;

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        grid = new int[R][C];

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                grid[i][j] = row.charAt(j) - 'A';
            }
        }

        visited[grid[0][0]] = true;
        dfs(new int[]{0, 0}, 1);
        System.out.println(ans);
    }

    private static void dfs(int[] now, int count) {
        ans = Math.max(ans, count);

        for (int i = 0; i < 4; i++) {
            int nx = now[0] + DX[i];
            int ny = now[1] + DY[i];
            if (canGo(nx, ny)) {
                visited[grid[nx][ny]] = true;
                dfs(new int[]{nx, ny}, count + 1);
                visited[grid[nx][ny]] = false;
            }
        }
    }

    private static boolean canGo(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C && !visited[grid[x][y]];
    }
}
