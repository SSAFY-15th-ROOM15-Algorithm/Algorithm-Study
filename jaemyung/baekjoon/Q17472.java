import java.util.*;

public class Q17472 {
    static int N, M, islandCount;
    static int[][] map;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[] parent;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
        @Override
        public int compareTo(Edge o) { return this.w - o.w; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        map = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) map[i][j] = sc.nextInt();

        // 1. 섬 라벨링 (1번 섬부터 시작)
        islandCount = 0;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    labelIslands(i, j, ++islandCount, visited);
                }
            }
        }

        // 2. 가능한 모든 다리 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) makeBridges(i, j, map[i][j]);
            }
        }

        // 3. 크루스칼 알고리즘 (MST)
        parent = new int[islandCount + 1];
        for (int i = 1; i <= islandCount; i++) parent[i] = i;

        int totalDist = 0, edgesUsed = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (union(e.u, e.v)) {
                totalDist += e.w;
                edgesUsed++;
            }
        }

        // 모든 섬이 연결되었는지 확인 (간선 수 = 정점 수 - 1)
        System.out.println(edgesUsed == islandCount - 1 ? totalDist : -1);
    }

    // 섬 번호 붙이기 (BFS)
    static void labelIslands(int r, int c, int num, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        map[r][c] = num;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i], nc = curr[1] + dc[i];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    map[nr][nc] = num;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    // 다리 놓아보기 (모든 방향으로 쭉 뻗어나감)
    static void makeBridges(int r, int c, int startIsland) {
        for (int i = 0; i < 4; i++) {
            int dist = 0;
            int nr = r, nc = c;
            while (true) {
                nr += dr[i]; nc += dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == startIsland) break;
                if (map[nr][nc] == 0) { // 바다라면 다리 길이 증가
                    dist++;
                } else { // 다른 섬을 만났다면
                    if (dist >= 2) pq.add(new Edge(startIsland, map[nr][nc], dist));
                    break;
                }
            }
        }
    }

    // 크루스칼용 Union-Find
    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    static boolean union(int a, int b) {
        int rootA = find(a), rootB = find(b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
            return true;
        }
        return false;
    }
}