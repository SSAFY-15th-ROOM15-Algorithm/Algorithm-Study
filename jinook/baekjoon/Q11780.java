package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q11780 {
    static final int INF = (int) 1e7;
    static int N;
    static int[][] grid;
    static int[][] path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        grid = new int[N + 1][N + 1];
        path = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                grid[i][j] = INF;
            }
            grid[i][i] = 0;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (cost < grid[u][v]) {
                grid[u][v] = cost;
                path[u][v] = v;
            }
        }

        System.out.print(floyd());
    }

    static String floyd() {
        StringBuilder sb = new StringBuilder();

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (grid[i][j] > grid[i][k] + grid[k][j]) {
                        grid[i][j] = grid[i][k] + grid[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (grid[i][j] == INF) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(grid[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        sb.append(trace());
        return sb.toString();
    }

    static String trace() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (path[i][j] == 0) {
                    sb.append(0).append("\n");
                    continue;
                }

                List<Integer> p = new ArrayList<>();
                int cur = i;
                p.add(cur);
                while (cur != j) {
                    cur = path[cur][j];
                    p.add(cur);
                }

                sb.append(p.size());
                for (int n : p) {
                    sb.append(" ").append(n);
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
