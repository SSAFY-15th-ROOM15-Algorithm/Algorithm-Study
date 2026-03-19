package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q11729 {
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        sb.append((1 << N) - 1).append("\n");
        moveBlock(1, 3, N);
        System.out.print(sb);
    }

    // n개의 block을 from 에서 to 로 이동
    private static void moveBlock(int from, int to, int n) {
        // base case : 블록이 1개면 그냥 이동하면 됨
        if (n == 1) {
            sb.append(from).append(" ").append(to).append("\n");
            return;
        }

        // n번째 블록의 위에 적재된 나머지 블록들을 출발지와 목적지가 아닌 남은 장대로 이동
        moveBlock(from, 6 - from - to, n - 1);
        // n번째 블록을 목적지로 이동
        sb.append(from).append(" ").append(to).append("\n");
        // 나머지 블록들도 목적지로 이동
        moveBlock(6 - from - to, to, n - 1);
    }

    // n = 1 에서 성림, n = k 일때 성립한다 가정했을 때, n = k + 1 에서 유효한가?
    // moveBlock(a,b,k)가 k개의 블록을 a에서 b로 잘 옮겨준다고 가정했으므로 유효함.
}
