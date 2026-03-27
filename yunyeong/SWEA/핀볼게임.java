package SWEA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class 핀볼게임 {
	static int N;
    // 방향 설정: 0(상), 1(하), 2(좌), 3(우) -> 0-1, 2-3이 각각 서로 반대 방향임
    static int dr[] = { -1, 1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };
    static int[][] map;

    // 블록별 방향 전환 정보: block[블록번호][현재방향] = 바뀔방향
    // 예: 1번 블록에서 상(0)으로 들어오면 하(1)로 나감
    static int[][] block = {
        { }, // 0번은 빈공간 (사용 안 함)
        {1, 3, 0, 2}, // 1번 블록 (ㄴ): 상->하, 하->우, 좌->상, 우->좌
        {3, 0, 1, 2}, // 2번 블록 (ㄱ반대): 상->우, 하->상, 좌->하, 우->좌
        {2, 0, 3, 1}, // 3번 블록 (ㄱ): 상->좌, 하->상, 좌->우, 우->하
        {1, 2, 3, 0}, // 4번 블록 (ㄴ반대): 상->하, 하->좌, 좌->우, 우->상
        {1, 0, 3, 2}  // 5번 블록 (정사각형): 모든 방향 반전
    };

    // 웜홀 쌍의 위치를 저장할 맵 (키: 웜홀번호 6~10, 값: 좌표 리스트)
    static Map<Integer, List<int[]>> wormhole;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 전체 테스트 케이스 개수
     
        for(int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt(); // 게임판 크기
            map = new int[N][N];
            wormhole = new HashMap<>();
             
            // 6~10번 웜홀을 위한 리스트 미리 생성
            for(int i = 6; i <= 10; i++) {
                wormhole.put(i, new ArrayList<>());
            }

            // 게임판 정보 입력 및 웜홀 위치 저장
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    // 6번 이상의 숫자면 웜홀이므로 해당 번호 리스트에 좌표 추가
                    if(map[i][j] >= 6){
                        wormhole.get(map[i][j]).add(new int[] {i, j});
                    }
                }
            }

            int ans = 0; // 최고 점수 저장용
            // 모든 칸을 순회하며 '빈 공간(0)'인 경우에만 시뮬레이션 시작
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if (map[i][j] == 0){
                        // 해당 칸에서 4방향(상, 하, 좌, 우)으로 다 쏴봄
                        for(int d = 0; d < 4; d++){
                            ans = Math.max(ans, play(i, j, d));
                        }
                    }
                }
            }
            System.out.println("#" + test_case + " " + ans);    
        }
    }

    // 실제 핀볼이 움직이는 시뮬레이션 함수
    static int play(int sx, int sy, int dir) {
        int x = sx; // 현재 x좌표
        int y = sy; // 현재 y좌표
        int score = 0; // 부딪힌 횟수
         
        while(true){
            // 1. 현재 방향으로 한 칸 전진
            x += dr[dir];
            y += dc[dir];
             
            // 2. 벽에 부딪힌 경우 처리
            if (x < 0 || y < 0 || x >= N || y >= N) {
                score++; // 점수 추가
                // 다시 벽 안으로 위치를 되돌림 (방향 반전 후 전진하기 위해)
                x -= dr[dir];
                y -= dc[dir];
                // 방향을 반대로 바꿈 (0<->1, 2<->3)
                dir = (dir == 0 ? 1 : (dir == 1 ? 0 : (dir == 2 ? 3 : 2)));       
            }

            // 3. 종료 조건 체크: 시작 위치로 돌아오거나 블랙홀(-1)을 만난 경우
            // (벽 처리를 먼저 했으므로 x, y가 맵 범위 안에 있는지 체크해야 안전함)
            if ((x == sx && y == sy) || (x >= 0 && x < N && y >= 0 && y < N && map[x][y] == -1)){
                return score; // 게임 종료, 현재까지의 점수 반환
            }

            // 4. 블록이나 웜홀 처리 (맵 범위 안일 때만)
            if (x >= 0 && x < N && y >= 0 && y < N){
                int cell = map[x][y];
                // 일반 블록(1~5)을 만난 경우
                if (cell >= 1 && cell <= 5) {
                    score++; // 점수 추가
                    dir = block[cell][dir]; // 미리 만들어둔 배열로 방향 전환
                } 
                // 웜홀(6~10)을 만난 경우
                else if (cell >= 6) {
                    List<int[]> list = wormhole.get(cell);
                    int[] p1 = list.get(0);
                    int[] p2 = list.get(1);
                    // 현재 위치가 웜홀의 p1이면 p2로 순간이동, 아니면 p1으로 순간이동
                    if (p1[0] == x && p1[1] == y ){
                        x = p2[0];
                        y = p2[1];
                    } else {
                        x = p1[0];
                        y = p1[1];
                    }
                    // 방향은 그대로 유지한 채 다음 루프에서 전진하게 됨
                }
            }
        }
    }
}
