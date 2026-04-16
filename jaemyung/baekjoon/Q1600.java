package asd;

import java.util.*;

public class Q1600 {
    static int n, m, k;
    static int[][] map;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int[] hdr = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] hdc = {-1, 1, -2, 2, -2, 2, -1, 1};
    static boolean[][][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        m = sc.nextInt();
        n = sc.nextInt();
        map = new int[n][m];
        visited = new boolean[n][m][k + 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        System.out.println(bfs());
    }
    
    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0, 0, 0});
        visited[0][0][0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            int ck = cur[2];
            int cd = cur[3];
            
            if (cr == n - 1 && cc == m - 1) return cd;
            
            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 0 && !visited[nr][nc][ck]) {
                    visited[nr][nc][ck] = true;
                    q.add(new int[] {nr, nc, ck, cd + 1});
                }
            }
            
            if (ck < k) {
                for (int i = 0; i < 8; i++) {
                    int nr = cr + hdr[i];
                    int nc = cc + hdc[i];
                    int nk = ck + 1;
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 0 && !visited[nr][nc][nk]) {
                        visited[nr][nc][nk] = true;
                        q.add(new int[] {nr, nc, nk, cd + 1});
                    }
                }
            }
        }
        return -1;
    }
}