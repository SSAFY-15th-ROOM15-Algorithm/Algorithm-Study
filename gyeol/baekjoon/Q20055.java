import java.util.*;

public class Q20055 {
	static int N, K;
	static int [] belt;
	static boolean [] robot;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		belt = new int [2 * N];
		robot = new boolean [2 * N];
		for(int i = 0; i < 2 * N; i++) {
			belt[i] = sc.nextInt();
		}
		
		int step = 1;
		while(true) {
			
			// 1. 벨트 회전
			int up = belt[2 * N - 1];
			boolean upR = robot[2 * N - 1];
			for(int i = 2 * N - 1 ; i > 0; i--) {
				belt[i] = belt[i - 1];
				robot[i] = robot[i - 1];
			}
			belt[0] = up;
			robot[0] = upR;
			
			robot[N - 1] = false; // 회전 후 내리는 위치 로봇 제거
			
			
			// 2. 로봇 이동
			for(int i = N - 2; i >= 0; i--) {
				if (robot[i] && !robot[i + 1] && belt[i + 1] > 0) { // 내리는 위치 바로 앞까지
                    robot[i] = false;
                    robot[i + 1] = true;
                    belt[i + 1]--;
                }
			}
			robot[N - 1] = false; // 내리는 위치 도착 시 제거
			
			
			// 3. 올리는 위치에 로봇 올리기
			if(belt[0] > 0) {
				robot[0] = true;
				belt[0]--;
				
			}
			
			
			// 4. 내구도 0인 칸 개수 확인
			int zero = 0;
			for(int i = 0; i < 2 * N; i++) {
				if(belt[i] == 0) zero++;
			}
			
			if(zero >= K) break;
			step++;
		}
		
		System.out.println(step);
		
	}
}
