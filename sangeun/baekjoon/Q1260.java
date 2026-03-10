package baekjoon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Q1260 {
    static boolean[] visited;
    static ArrayList<Integer>[] arr;

    public static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int next : arr[v]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    public static void bfs(int v) {
        Deque<Integer> q = new ArrayDeque<>();

        visited[v] = true;
        q.add(v);

        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.print(curr + " ");

            for (int next : arr[curr]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int V = sc.nextInt();

        arr = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            arr[a].add(b);
            arr[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(arr[i]);
        }

        dfs(V);
        System.out.println();

        visited = new boolean[N + 1];
        bfs(V);
    }
}
