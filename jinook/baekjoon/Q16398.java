package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q16398 {
    static final int INF = (int) 1e8 + 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] adj = new int[N][N];

        int[] minDist = new int[N];
        boolean[] visited = new boolean[N];

        Arrays.fill(minDist, INF);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        minDist[0] = 0;
        long cost = 0;

        for (int i = 0; i < N; i++) {
            int currentMin = INF;
            int cur = -1;

            for (int j = 0; j < N; j++) {
                if (!visited[j] && minDist[j] < currentMin) {
                    currentMin = minDist[j];
                    cur = j;
                }
            }

            visited[cur] = true;
            cost += currentMin;

            for (int j = 0; j < N; j++) {
                if (!visited[j] && minDist[j] > adj[cur][j]) {
                    minDist[j] = adj[cur][j];
                }
            }
        }

        System.out.println(cost);
    }
}
