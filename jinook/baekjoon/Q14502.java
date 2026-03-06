package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q14502 {
    private static int N, M;
    private static int[][] initialGrid; // 초기 격자 저장
    private static int[][] grid; // 벽이 세워진 격자 정보도 전역으로 관리
    private static int safe = -3; // 3개의 안전 지역은 벽으로 대체될 것이기 때문에 미리 3빼기
    private static int ans = 0;

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        initialGrid = new int[N][M];

        List<int[]> startPoints = new ArrayList<>(); // 바이러스 전파 시작점 저장
        List<int[]> changeable = new ArrayList<>(); // 벽이 세워질 수 있는 모든 지점(0) 저장

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int now = Integer.parseInt(st.nextToken());
                if (now == 0) {
                    safe++;
                    changeable.add(new int[]{i, j});
                } else if (now == 2) {
                    startPoints.add(new int[]{i, j});
                }
                initialGrid[i][j] = now;
            }
        }

        solution(changeable, startPoints);
        System.out.println(ans);
    }

    private static void solution(List<int[]> changeable, List<int[]> startPoints) {
        int len = changeable.size();

        // 벽은 3개만 세우면 되므로 n개중 3개 고르는 combination을 그냥 반복문으로 구현
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    grid = new int[N][M];
                    for (int x = 0; x < N; x++) {
                        for (int y = 0; y < M; y++) {
                            grid[x][y] = initialGrid[x][y];
                        }
                    }

                    grid[changeable.get(i)[0]][changeable.get(i)[1]] = 1;
                    grid[changeable.get(j)[0]][changeable.get(j)[1]] = 1;
                    grid[changeable.get(k)[0]][changeable.get(k)[1]] = 1;

                    bfs(startPoints);
                }
            }
        }
    }

    private static void bfs(List<int[]> startPoints) {
        Queue<int[]> q = new ArrayDeque<>(startPoints);
        int count = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + DX[i];
                int ny = now[1] + DY[i];
                if (canGo(nx, ny)) {
                    // 전파 횟수 1 증가
                    count++;
                    // 이미 여태까지 구한 안전지역 개수보다 작아지면 바로 리턴
                    if (safe - count <= ans) {
                        return;
                    }
                    // 감염처리 후 계속 전파를 위해 q에 삽입
                    grid[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        ans = Math.max(ans, safe - count);
    }

    private static boolean canGo(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M && grid[x][y] == 0;
    }
}
