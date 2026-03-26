package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q15683 {

    static int N, M;
    static int ans = 64;
    static List<CCTV> cctvs = new ArrayList<>();

    // 상, 우, 하, 좌
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, 1, 0, -1};

    static class CCTV {
        int x, y, type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        dfs(0, map);
        System.out.println(ans);
    }

    static void dfs(int idx, int[][] map) {
        // base case : 모든 cctv로 감시 완료한 상태
        if (idx == cctvs.size()) {
            ans = Math.min(ans, countBlindSpot(map));
            return;
        }

        // 현재 cctv에 대해 모든 방향에 대한 감시 이후 다음 cctv로 넘겨주기
        CCTV cctv = cctvs.get(idx);
        int type = cctv.type;

        for (int dir = 0; dir < 4; dir++) {
            int[][] nextMap = deepCopy(map);

            if (type == 1) {
                watch(cctv.x, cctv.y, dir, nextMap);
            } else if (type == 2) {
                if (dir >= 2) {
                    continue;
                }
                watch(cctv.x, cctv.y, dir, nextMap);
                watch(cctv.x, cctv.y, dir + 2, nextMap);
            } else if (type == 3) {
                watch(cctv.x, cctv.y, dir, nextMap);
                watch(cctv.x, cctv.y, (dir + 1) % 4, nextMap);
            } else if (type == 4) {
                watch(cctv.x, cctv.y, dir, nextMap);
                watch(cctv.x, cctv.y, (dir + 1) % 4, nextMap);
                watch(cctv.x, cctv.y, (dir + 2) % 4, nextMap);
            } else {
                if (dir >= 1) {
                    continue;
                }
                watch(cctv.x, cctv.y, dir, nextMap);
                watch(cctv.x, cctv.y, dir + 1, nextMap);
                watch(cctv.x, cctv.y, dir + 2, nextMap);
                watch(cctv.x, cctv.y, dir + 3, nextMap);
            }

            dfs(idx + 1, nextMap);
        }
    }

    static void watch(int x, int y, int dir, int[][] map) {
        int nx = x + DX[dir];
        int ny = y + DY[dir];

        while (inRange(nx, ny) && map[nx][ny] != 6) {
            if (map[nx][ny] == 0) {
                map[nx][ny] = -1;
            }
            nx += DX[dir];
            ny += DY[dir];
        }
    }

    static int countBlindSpot(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = original[i][j];
            }
        }

        return copy;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
