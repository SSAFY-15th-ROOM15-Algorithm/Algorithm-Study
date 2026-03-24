package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2230 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int s = 0;
        int e = 0;
        int ans = (int) (2 * 1e9 + 1);

        Arrays.sort(arr);
        while (s < N && e < N) {
            int diff = arr[e] - arr[s];
            if (diff == M) {
                System.out.println(M);
                return;
            } else if (diff > M) {
                ans = Math.min(diff, ans);
                s++;
            } else {
                e++;
            }
        }

        System.out.println(ans);
    }
}
