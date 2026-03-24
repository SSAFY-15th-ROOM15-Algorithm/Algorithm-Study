package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Q2295 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Set<Integer> two = new HashSet<>();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                two.add(arr[i] + arr[j]);
            }
        }

        Arrays.sort(arr);

        // a[i] + a[j] + a[k] = a[l]인 max l 구하기
        // two[m] + arr[k] = arr[l] 인 max l 구하기로 변환 가능 O(N^2)
        // arr[l] - arr[k] 가 two 배열에 있는지를 찾는 문제로 변환 가능
        // hashset을 이용하면 O(1)에 contains 여부를 알 수 있으므로 전체 시간복잡도를 O(N^2)로 줄일 수 있음
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < N; j++) {
                if (two.contains(arr[i] - arr[j])) { // O(1)
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }
}
