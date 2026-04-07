import java.util.*;

public class Q18352 {

    static int N, M, K, X;
    static List<List<Integer>> graph;
    static int[] dist;
    

    static void bfs(int start) {
    	Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int i = 0; i < graph.get(curr).size(); i++) {
            	int next = graph.get(curr).get(i);
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    queue.offer(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        X = sc.nextInt();

        graph = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            graph.get(A).add(B);
        }
        dist = new int[N + 1];
        Arrays.fill(dist, -1);
        
        bfs(X);

        boolean found = false;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                System.out.println(i);
                found = true;
            }
        }

        if (!found) {
            System.out.println(-1);
        }
    }
}