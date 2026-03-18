import java.util.*;
import java.io.*;

public class Q7569 {
    static int n, m, h, res;
    static int[][][] tomato;
    static int[] dr = {0, 0, 0, 0, 1, -1};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dh = {1, -1, 0, 0, 0, 0};    
    static Queue<int[]> q;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        
        q = new ArrayDeque<>();
        tomato = new int[n][m][h];
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    int t = Integer.parseInt(st.nextToken());
                    tomato[j][k][i] = t;
                    if (t == 1) {
                        q.offer(new int[] {j, k, i, 0});
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int curR = tmp[0];
            int curC = tmp[1];
            int curH = tmp[2];
            int day = tmp[3];
            
            for (int i = 0; i < 6; i++) {
                int nr = curR + dr[i];
                int nc = curC + dc[i];
                int nh = curH + dh[i];
                
                if (canGo(nr, nc, nh)) {
                    tomato[nr][nc][nh] = 1;
                    q.offer(new int[] {nr, nc, nh, day + 1});
                    res = Math.max(res, day + 1);
                }
            }
        }
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (tomato[j][k][i] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        
        System.out.println(res);
    }
    
    static boolean canGo(int r, int c, int height) {
        return r >= 0 && r < n && c >= 0 && c < m && height >= 0 && height < h && tomato[r][c][height] == 0;
    }
}