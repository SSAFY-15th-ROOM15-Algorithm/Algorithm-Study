import java.util.Scanner;

public class Q10819 {
    static int N;
    static int[] input;     // 입력받은 숫자 배열
    static int[] selected;  // 순열로 나열된 숫자 배열
    static boolean[] visited;
    static int maxResult = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        input = new int[N];
        selected = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        permutation(0);

        System.out.println(maxResult);
    }

    static void permutation(int cnt) {
        // N개의 숫자를 모두 나열했을 때 계산 후 최댓값 갱신
        if (cnt == N) {
            int currentSum = 0;
            for (int i = 0; i < N - 1; i++) {
                currentSum += Math.abs(selected[i] - selected[i + 1]);
            }
            
            if (currentSum > maxResult) {
                maxResult = currentSum;
            }
            return;
        }

        // 재귀 파트
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[cnt] = input[i]; 
            permutation(cnt + 1);
            visited[i] = false; 
        }
    }
}