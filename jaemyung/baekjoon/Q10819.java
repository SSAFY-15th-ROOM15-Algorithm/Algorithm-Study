<<<<<<< HEAD
package codetree;

import java.util.Scanner;

public class Main {
    static int N;
    static int[] input;     // 입력받은 숫자 배열
    static int[] selected;  // 순열로 나열된 숫자 배열
    static boolean[] visited; // 방문 체크
=======
import java.util.Scanner;

public class Q10819 {
    static int N;
    static int[] input;     // 입력받은 숫자 배열
    static int[] selected;  // 순열로 나열된 숫자 배열
    static boolean[] visited;
>>>>>>> 9b35c791e9a6f296e13c73f580058ec1a2955876
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

<<<<<<< HEAD
        // 순열 생성 시작
=======
>>>>>>> 9b35c791e9a6f296e13c73f580058ec1a2955876
        permutation(0);

        System.out.println(maxResult);
    }

    static void permutation(int cnt) {
<<<<<<< HEAD
        // 1. 기저 조건: N개의 숫자를 모두 나열했을 때
        if (cnt == N) {
            // 여기에 직접 계산 로직 삽입
            int currentSum = 0;
            for (int i = 0; i < N - 1; i++) {
                // Math.abs를 이용해 인접한 요소 간의 차이의 절댓값을 합산
                currentSum += Math.abs(selected[i] - selected[i + 1]);
            }
            
            // 기존 최댓값과 비교하여 갱신
=======
        // N개의 숫자를 모두 나열했을 때 계산 후 최댓값 갱신
        if (cnt == N) {
            int currentSum = 0;
            for (int i = 0; i < N - 1; i++) {
                currentSum += Math.abs(selected[i] - selected[i + 1]);
            }
            
>>>>>>> 9b35c791e9a6f296e13c73f580058ec1a2955876
            if (currentSum > maxResult) {
                maxResult = currentSum;
            }
            return;
        }

<<<<<<< HEAD
        // 2. 재귀 파트: 현재 자리에 들어갈 숫자 선택
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;        // 사용 처리
            selected[cnt] = input[i]; // 현재 depth(cnt)에 숫자 배치
            
            permutation(cnt + 1);     // 다음 자리로 재귀 호출
            
            visited[i] = false;       // 재귀에서 돌아오면 사용 해제 (백트래킹 핵심)
=======
        // 재귀 파트
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[cnt] = input[i]; 
            permutation(cnt + 1);
            visited[i] = false; 
>>>>>>> 9b35c791e9a6f296e13c73f580058ec1a2955876
        }
    }
}