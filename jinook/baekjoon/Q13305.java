package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// greedy 알고리즘을 배우고 써보기 좋은 문제
// 기름 값을 언제 새로 갱신해 주는지를 잘 떠올려보자
// 자료형을 선택할 때 입력값을 항상 신경쓰는 것을 습관으로!
public class Q13305 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dist = new int[N - 1];
        int[] costs = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        long cost = 0;
        int i = 0;
        int currentMin = Integer.MAX_VALUE;

        while (i < N - 1) {
            // 기존까지와 비교해 기름값이 더 저렴한 지역을 발견하면 기름값 갱신(greedy)
            if (currentMin > costs[i]) {
                currentMin = costs[i];
            }
            cost += (long) currentMin * dist[i++];
        }

        System.out.println(cost);
    }
}
