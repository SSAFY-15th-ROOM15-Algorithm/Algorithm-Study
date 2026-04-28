public class Q31575 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static boolean dfs(int r, int c) {
        // 목적지 도착
        if (r == M - 1 && c == N - 1) {
            return true;
        }

        // 현재 위치 방문 처리
        visited[r][c] = true;

        // 오른쪽으로 이동
        int nextC = c + 1;
        if (nextC < N && map[r][nextC] == 1 && !visited[r][nextC]) {
            if (dfs(r, nextC)) return true;
        }

        // 아래쪽으로 이동
        int nextR = r + 1;
        if (nextR < M && map[nextR][c] == 1 && !visited[nextR][c]) {
            if (dfs(nextR, c)) return true;
        }

        return false;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = sc.nextInt();

        if (dfs(0, 0))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}