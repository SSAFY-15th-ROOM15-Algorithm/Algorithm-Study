import java.util.Scanner;

public class Q16173 {
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static boolean dfs(int x, int y) {
    	
    	//map 범위를 벗어났으면
    	if(x < 0 || x >= N || y < 0 || y >= N) return false;
    	
    	//이미 방문했으면
    	if(visited[x][y]) return false;
    	
    	//(3,3)에 도달했으면
    	if(map[x][y] == -1) return true;
    	
    	
    	visited[x][y] = true;    	
        int move = map[x][y];

        if (move == 0) return false;
        return dfs(x + move, y) || dfs(x, y + move);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        if (dfs(0, 0)) {
            System.out.println("HaruHaru");
        } else {
            System.out.println("Hing");
        }
    }
}