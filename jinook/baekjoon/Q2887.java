package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q2887 {
    static int N;
    static int[] parent;
    static int[] size;

    static class Planet {
        int idx, x, y, z;

        public Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        size = new int[N];
        Planet[] planets = new Planet[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            planets[i] = new Planet(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(planets, (p1, p2) -> Integer.compare(p1.x, p2.x));
        for (int i = 0; i < N - 1; i++) {
            edges.add(new Edge(planets[i].idx, planets[i + 1].idx, Math.abs(planets[i + 1].x - planets[i].x)));
        }

        Arrays.sort(planets, (p1, p2) -> Integer.compare(p1.y, p2.y));
        for (int i = 0; i < N - 1; i++) {
            edges.add(new Edge(planets[i].idx, planets[i + 1].idx, Math.abs(planets[i + 1].y - planets[i].y)));
        }

        Arrays.sort(planets, (p1, p2) -> Integer.compare(p1.z, p2.z));
        for (int i = 0; i < N - 1; i++) {
            edges.add(new Edge(planets[i].idx, planets[i + 1].idx, Math.abs(planets[i + 1].z - planets[i].z)));
        }

        Collections.sort(edges);

        int ans = 0;
        int cnt = 0;
        for (Edge edge : edges) {
            if (cnt == N - 1) {
                break;
            }

            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                ans += edge.cost;
                cnt++;
            }
        }

        System.out.println(ans);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (size[rootX] > size[rootY]) {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            } else {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
        }
    }
}
