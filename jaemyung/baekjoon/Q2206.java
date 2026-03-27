package asd;
import java.util.*;

public class Q2206 {
    static int n, m;
    static int[][] map;
    static int[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        visited = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[]{0, 0, 0});
        visited[0][0][0] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int b = cur[2];

            if (r == n - 1 && c == m - 1) {
            	return visited[r][c][b];
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                	continue;
                }

                if (map[nr][nc] == 0 && visited[nr][nc][b] == 0) {
                    visited[nr][nc][b] = visited[r][c][b] + 1;
                    q.add(new int[]{nr, nc, b});
                }

                if (map[nr][nc] == 1 && b == 0 && visited[nr][nc][1] == 0) {
                    visited[nr][nc][1] = visited[r][c][0] + 1;
                    q.add(new int[]{nr, nc, 1});
                }
            }
        }
        return -1;
    }
}