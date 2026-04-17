import java.util.*;

public class make1 {
    static int N;

    public static void make1(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[1000000];
        int[] dist = new int[1000000];

        q.offer(N);
        visited[N] = true;
        dist[N] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == 1) {
                System.out.println(dist[cur]);
                break;
            }

            List<Integer> nexts = new ArrayList<>();
            nexts.add(cur - 1);
            nexts.add(cur + 1);
            if (cur % 2 == 0) nexts.add(cur / 2);
            if (cur % 3 == 0) nexts.add(cur / 3);

            for (int next : nexts) {
                if (next >= 1 && next <= 1000000 && !visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }
    }
}