package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q14502 {
    private static int N, M;
    private static int[][] grid;
    private static int[][] visited;
    private static int visitId = 0; // 방문 처리를 위한 배열을 매번 초기화 하지 않고, 방문 id를 늘려가며 gc 부하를 줄이는 기법, 알아두면 유용할듯

    private static final List<int[]> startPoints = new ArrayList<>();
    private static final List<int[]> emptySpaces = new ArrayList<>();

    private static int safe = -3; // 3개의 안전 지역은 벽으로 대체될 것이기 때문에 미리 3빼기
    private static int ans = 0;

    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int now = Integer.parseInt(st.nextToken());
                if (now == 0) {
                    safe++;
                    emptySpaces.add(new int[]{i, j});
                } else if (now == 2) {
                    startPoints.add(new int[]{i, j});
                }
                grid[i][j] = now;
            }
        }

        selectWalls(0, 0);
        System.out.println(ans);
    }


    private static void selectWalls(int start, int count) {
        if (count == 3) { // 3개 세웠으면 bfs 탐색 진행 후 리턴
            bfs();
            return;
        }

        for (int i = start; i < emptySpaces.size(); i++) {
            int[] pos = emptySpaces.get(i);
            grid[pos[0]][pos[1]] = 1; // 벽 하나 세우기
            selectWalls(i + 1, count + 1);
            grid[pos[0]][pos[1]] = 0; // 세운 벽 되돌리기(backtrack)
        }
    }

    private static void bfs() {
        visitId++; // 방문배열 초기화 하지 말고 방문 id 증가시키는 기법을 사용하여 gc 부담 줄이기
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
                    visited[nx][ny] = visitId;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        ans = Math.max(ans, safe - count);
    }

    private static boolean canGo(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M && grid[x][y] == 0 && visited[x][y] != visitId;
    }
}
