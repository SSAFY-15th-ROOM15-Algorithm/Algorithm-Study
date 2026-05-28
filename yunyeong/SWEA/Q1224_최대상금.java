package SWEA;

import java.io.*;
import java.util.*;

public class Q1224_최대상금 {

    static int answer;
    static int swapCount;
    static Set<String>[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String number = st.nextToken();
            swapCount = Integer.parseInt(st.nextToken());

            answer = 0;

            visited = new HashSet[swapCount + 1];
            for (int i = 0; i <= swapCount; i++) {
                visited[i] = new HashSet<>();
            }

            dfs(number.toCharArray(), 0);

            System.out.println("#" + tc + " " + answer);
        }
    }

    static void dfs(char[] arr, int depth) {
        String current = new String(arr);

        if (visited[depth].contains(current)) {
            return;
        }

        visited[depth].add(current);

        if (depth == swapCount) {
            answer = Math.max(answer, Integer.parseInt(current));
            return;
        }

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(arr, i, j);
                dfs(arr, depth + 1);
                swap(arr, i, j);
            }
        }
    }

    static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}