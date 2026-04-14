import java.util.Scanner;

public class Q1012 {
	static int M, N, K;
	static int [][] map;
	static boolean [][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if (map[nr][nc] == 1 && !visited[nr][nc]) {
                    dfs(nr, nc);
                }
            }
        }
	}
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 0; tc < T; tc++) {
            M = sc.nextInt(); 
            N = sc.nextInt();
            K = sc.nextInt();

            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                int c = sc.nextInt();
                int r = sc.nextInt();
                map[r][c] = 1;
            }

            int count = 0;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (map[r][c] == 1 && !visited[r][c]) {
                        dfs(r, c);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }
}