import java.util.*;

public class 빙하 {
    static int n, m;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int time = 0;
        int lastCnt = 0;
        while (true) {
            int cnt = melt();
            
            if (cnt == 0) break;
            
            lastCnt = cnt;
            time++;
        }
        System.out.println(time + " " + lastCnt);
    }

    public static int melt() {
        visited = new boolean[n][m];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;

        int cnt = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    visited[nx][ny] = true;

                    if (grid[nx][ny] == 0) {
                        q.add(new int[]{nx, ny});
                    } else {
                        grid[nx][ny] = 0;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}