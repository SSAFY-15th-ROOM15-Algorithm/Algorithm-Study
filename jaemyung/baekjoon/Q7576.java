import java.util.*;

public class Q7576 {
    static int n, m, cnt;
    static int[][] graph;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        graph = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
                if (graph[i][j] == 1) {
                    q.offer(new int[] {i, j});
                }
            }
        }
        
        cnt = 0;
        bfs();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 0) {
                    System.out.println("-1");
                    return;
                }
            }
        }

        System.out.println(cnt);
    }
    
    static void bfs() {
        while(!q.isEmpty()) {
            int len = q.size();
            boolean changed = false;
            
            for (int i = 0; i < len; i++) {
                int[] tmp = q.poll();
                int curR = tmp[0];
                int curC = tmp[1];
                
                for (int j = 0; j < 4; j++) {
                    int nr = curR + dr[j];
                    int nc = curC + dc[j];
                    
                    if(0 <= nr && nr < m && 0 <= nc && nc < n && graph[nr][nc] == 0) {
                        graph[nr][nc] = 1;
                        changed = true;
                        q.offer(new int[] {nr, nc});
                    }
                }
            }

            if(changed) {
                cnt++;
            }
        }
    }
}