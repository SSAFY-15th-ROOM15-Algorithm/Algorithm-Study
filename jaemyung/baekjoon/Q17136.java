package asd;

import java.util.*;

public class Q17136 {
    static int[][] map = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) map[i][j] = sc.nextInt();

        dfs(0, 0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void dfs(int r, int c, int cnt) {
        if (r >= 9 && c > 9) { // 끝까지 도달한 경우
            ans = Math.min(ans, cnt);
            return;
        }

        if (cnt >= ans) return; // 가지치기: 현재 최솟값보다 많이 쓰면 중단

        if (c > 9) { // 줄 바꿈
            dfs(r + 1, 0, cnt);
            return;
        }

        if (map[r][c] == 1) {
            // 큰 종이(5)부터 작은 종이(1)까지 시도
            for (int i = 5; i >= 1; i--) {
                if (paper[i] > 0 && canAttach(r, c, i)) {
                    update(r, c, i, 0); // 종이 붙이기
                    paper[i]--;
                    dfs(r, c + 1, cnt + 1);
                    paper[i]++;
                    update(r, c, i, 1); // 종이 떼기 (복구)
                }
            }
        } else {
            dfs(r, c + 1, cnt); // 0이면 다음 칸으로
        }
    }

    static boolean canAttach(int r, int c, int size) {
        if (r + size > 10 || c + size > 10) return false;
        for (int i = r; i < r + size; i++)
            for (int j = c; j < c + size; j++)
                if (map[i][j] == 0) return false;
        return true;
    }

    static void update(int r, int c, int size, int val) {
        for (int i = r; i < r + size; i++)
            for (int j = c; j < c + size; j++)
                map[i][j] = val;
    }
}