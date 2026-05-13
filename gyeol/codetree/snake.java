import java.util.*;

public class snake {
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 격자 크기
		int M = sc.nextInt(); // 사과의 개수
		int K = sc.nextInt(); // 뱀의 방향 변환 횟수
		
		int [][] board = new int [N][N];
		
		for(int i = 0; i < M; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			board[x][y] = 1;
		}
		
		// 뱀 몸 저장
        Deque<int[]> snake = new LinkedList<>();
        snake.add(new int[]{0, 0});
        board[0][0] = 2;
        
        int x = 0; int y = 0;
		int time = 0;
		boolean end = false;
		
		for(int i = 0; i < K; i++) {
			char d = sc.next().charAt(0);
			int p = sc.nextInt(); // 뱀이 얼마나 움직일지
			
			int dir = 0;
			
			if(d == 'U') dir = 0;
			else if(d == 'D') dir = 1;
			else if(d == 'L') dir = 2;
			else if(d == 'R') dir = 3;
			
			for(int j = 0; j < p; j++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				time++;
				
				// 벽 충돌 || 자기 몸 충돌
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 2) {
					end = true;
					break;
				}
				
				// 사과 있으면
				if(board[nx][ny] == 1) {
					snake.add(new int [] {nx, ny});
					board[nx][ny] = 2;
				}
				
				// 사과 없으면
				else {
					snake.add(new int [] {nx, ny});
					board[nx][ny] = 2;
					
					int [] tail = snake.pollFirst();
					int rx = tail[0]; int ry = tail[1];
					
					board[rx][ry] = 0;
				}
				
				x = nx; y = ny;
			}
			if(end) break;
		}
		System.out.println(time);
	}
}
