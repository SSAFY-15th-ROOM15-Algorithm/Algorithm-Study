package asd;

import java.util.*;

public class Q14567 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        int[] degree = new int[N + 1];
        int[] semester = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            adj[A].add(B);
            degree[B]++;
        }
        
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                q.offer(i);
                semester[i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int curr = q.poll();
            
            for (int next : adj[curr]) {
                degree[next]--;
                
                if (degree[next] == 0) {
                    semester[next] = semester[curr] + 1;
                    q.offer(next);
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            System.out.print(semester[i] + " ");
        }
    }
}