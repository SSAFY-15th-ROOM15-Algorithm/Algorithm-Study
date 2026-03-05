package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q24060 {
    private static int[] temp;
    private static int answer = -1;
    private static int K;
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        temp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(arr, 0, N - 1);
        System.out.println(answer);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        // 이미 답을 구했으면 정렬을 더 수행할 필요가 없음.
        if (answer != -1) {
            return;
        }

        // 기존 병합 정렬과 동일
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        // left, right 배열을 따로 만들지 말고, static temp 배열에 정렬해서 채우기
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        // 남은 값들 일괄적으로 채우기
        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        while (j <= right) {
            temp[t++] = arr[j++];
        }

        // count 늘려가면서 temp에 정렬해놓은것 arr에 하나씩 갱신
        i = left;
        t = 0;
        while (i <= right) {
            count++;
            // 답을 구했다면 바로 리턴
            if (count == K) {
                answer = temp[t];
                return;
            }
            arr[i++] = temp[t++];
        }
    }
}
