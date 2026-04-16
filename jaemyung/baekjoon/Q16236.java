package asd;

import java.util.*;

public class Q16236 {
    static int n, ans = 0, eaten = 0, size = 2;
    static int[][] map;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int sharkR, sharkC;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    sharkR = i;
                    sharkC = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            if (!bfs()) break;
        }
        System.out.println(ans);
    }

    static boolean bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] != o2[2]) return o1[2] - o2[2]; // 1. 거리순
            if (o1[0] != o2[0]) return o1[0] - o2[0]; // 2. 위쪽(r)
            return o1[1] - o2[1];                   // 3. 왼쪽(c)
        });

        boolean[][] visited = new boolean[n][n];
        pq.add(new int[]{sharkR, sharkC, 0});
        visited[sharkR][sharkC] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cr = cur[0];
            int cc = cur[1];
            int dist = cur[2];

            if (map[cr][cc] > 0 && map[cr][cc] < size) {
                map[cr][cc] = 0;
                sharkR = cr;
                sharkC = cc;
                ans += dist;
                eaten++;
                if (eaten == size) {
                    size++;
                    eaten = 0;
                }
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && map[nr][nc] <= size) {
                    visited[nr][nc] = true;
                    pq.add(new int[]{nr, nc, dist + 1});
                }
            }
        }

        return false;
    }
}