import java.util.*;

public class Q16234 {
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static ArrayList<int[]> union;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int days = 0;
        while (true) {
            boolean isMoved = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = bfs(i, j);

                        if (union.size() > 1) {
                            isMoved = true;
                            int newPop = sum / union.size();
                            for (int[] pos : union) {
                                map[pos[0]][pos[1]] = newPop;
                            }
                        }
                    }
                }
            }

            if (!isMoved) break;
            days++;
        }

        System.out.println(days);
    }

    static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        union = new ArrayList<>();
        
        int[] start = {r, c};
        q.offer(start);
        union.add(start);
        visited[r][c] = true;
        int sum = map[r][c];

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    int diff = Math.abs(map[cur[0]][cur[1]] - map[nr][nc]);
                    if (diff >= L && diff <= R) {
                        visited[nr][nc] = true;
                        int[] next = {nr, nc};
                        q.offer(next);
                        union.add(next);
                        sum += map[nr][nc];
                    }
                }
            }
        }
        return sum;
    }
}