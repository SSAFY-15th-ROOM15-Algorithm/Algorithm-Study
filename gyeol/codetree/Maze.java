import java.util.*;

public class Maze {
	static int[] dx = { 0, 1, 0, -1 }; // 시계방향 (동남서북)
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int x = sc.nextInt() - 1;
		int y = sc.nextInt() - 1;

		char[][] board = new char[N][N];
		boolean[][][] visited = new boolean[N][N][4];

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < N; j++) {
				board[i][j] = str.charAt(j); // # : 벽, . : 벽x
			}
		}

		int dir = 0;
		int time = 0;
		

		while (true) {
			// 같은 상태 재방문 -> 무한루프
            if (visited[x][y][dir]) {
                System.out.println(-1);
                return;
            }
            
            visited[x][y][dir] = true;
            
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			// 1. 앞이 막혀있으면 반시계 회전
			if (nx >= 0 && ny >= 0 && nx < N && ny < N && board[nx][ny] == '#') {
				dir = (dir + 3) % 4;
				continue;
			}
			
			// 2-1. 밖으로 나가면 탈출
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
				time++;
				break;
			}
			
			// 이동
			x = nx;
			y = ny;
			time++;
			
			 int rightDir = (dir + 1) % 4;
			 
			 int rx = x + dx[rightDir];
			 int ry = y + dy[rightDir];
			 
			 // 2-2. 오른쪽이 벽이면 진행
			 if(rx >= 0 && ry >= 0 && rx < N && ry < N && board[rx][ry] == '#') continue;
			 // 2-3. 오른쪽에 벽이 아니면
			 else {
				 // 시계 방향 회전
				 dir = rightDir;
				 
				 // 한 칸 더 이동
				 x += dx[dir];
				 y += dy[dir];
				 time++;
	        }
		}
		System.out.println(time);
	}
}
