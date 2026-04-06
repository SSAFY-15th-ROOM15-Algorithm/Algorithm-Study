package asd;
import java.io.*;
import java.util.*;

public class Q1197 {
    static int v, e;
    static int[] arr;

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[e];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, cost);
        }

        Arrays.sort(edges);

        arr = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            arr[i] = i;
        }

        int res = 0;
        int pick = 0;

        for (int i = 0; i < e; i++) {
            if (findSet(edges[i].from) != findSet(edges[i].to)) {
                union(edges[i].from, edges[i].to);
                res += edges[i].cost;
                pick++;
                if (pick == v - 1) {
                	break;
                }
            }
        }

        System.out.println(res);
    }

    static void union(int x, int y) {
        int rootX = findSet(x);
        int rootY = findSet(y);
        if (rootX != rootY) {
            arr[rootY] = rootX;
        }
    }

    static int findSet(int x) {
        if (x == arr[x]) {
        	return x;
        }
        arr[x] = findSet(arr[x]);
        return arr[x]; 
    }
}