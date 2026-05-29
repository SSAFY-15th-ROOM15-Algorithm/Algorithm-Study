package baekjoon;

import java.io.*;
import java.util.*;

public class Q1766 {
    
    static int N, M;
    static List<Integer>[] adj;
    static int[] indegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        indegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            indegree[v]++;
        }

        System.out.print(solution());
    }

    static String solution() {
        StringBuilder sb = new StringBuilder();

        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr).append(" ");

            for (int neighbor : adj[curr]) {
                if (--indegree[neighbor] == 0) {
                    pq.offer(neighbor);
                }
            }
        }

        return sb.toString();
    }
}
