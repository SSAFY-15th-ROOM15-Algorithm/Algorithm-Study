package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1806 {
    public static final int INF = (int) 1e5 + 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] preSum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = 1;
        int ans = INF;

        while (s < N + 1 && e < N + 1) {
            int diff = preSum[e] - preSum[s];

            if (diff >= S) {
                ans = Math.min(ans, e - s);
                s++;
            } else {
                e++;
            }
        }

        if (ans == INF) {
            System.out.println(0);
            return;
        }
        System.out.println(ans);
    }
}
