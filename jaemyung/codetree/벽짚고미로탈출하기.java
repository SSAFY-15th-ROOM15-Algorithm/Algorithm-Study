import java.util.Scanner;

public class 벽짚고미로탈출하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt() - 1;
        int y = sc.nextInt() - 1;
        char[][] maze = new char[n][n];
        
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < n; j++) {
                maze[i][j] = line.charAt(j);
            }
        }
        
        boolean[][][] visited = new boolean[n][n][4];
        
        int ans = 0;
        int dir = 0; // 0:우, 1:하, 2:좌, 3:상
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        
        while (true) {
            if (visited[x][y][dir]) {
                System.out.println("-1");
                return;
            }
            visited[x][y][dir] = true;

            int nx = x + dr[dir];
            int ny = y + dc[dir];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n && maze[nx][ny] == '#') {
                dir = (dir + 3) % 4;
                continue;
            }

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                ans++;
                System.out.println(ans);
                return;
            }

            int rightDir = (dir + 1) % 4;
            int rx = nx + dr[rightDir];
            int ry = ny + dc[rightDir];
            
            if (rx >= 0 && rx < n && ry >= 0 && ry < n && maze[rx][ry] == '#') {
                x = nx;
                y = ny;
                ans++;
            } else {
                x = nx;
                y = ny;
                ans++;

                dir = rightDir;
                x = x + dr[dir];
                y = y + dc[dir];
                ans++;
                
                if (x < 0 || x >= n || y < 0 || y >= n) {
                    System.out.println(ans);
                    return;
                }
            }
        }
    }
}