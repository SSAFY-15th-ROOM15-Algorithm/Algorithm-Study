import java.util.*;

public class Q1967 {
    static int n;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int maxDist = 0;

    static class Node {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (!sc.hasNextInt()) return;
        n = sc.nextInt();
        
        if (n == 1) {
            System.out.println(0);
            return;
        }

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt();
            int c = sc.nextInt();
            int w = sc.nextInt();
            graph[p].add(new Node(c, w));
            graph[c].add(new Node(p, w));
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            dfs(i, 0);
        }

        System.out.println(maxDist);
    }

    static void dfs(int current, int dist) {
        visited[current] = true;

        if (dist > maxDist) {
            maxDist = dist;
        }

        for (Node next : graph[current]) {
            if (!visited[next.to]) {
                dfs(next.to, dist + next.weight);
            }
        }
    }
}