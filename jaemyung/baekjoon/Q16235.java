package asd;

import java.util.*;

public class Q16235 {
    static int N, M, K;
    static int[][] A, cur;
    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};
    static Deque<Integer>[][] trees;
    static Queue<int[]> deadTrees = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        A = new int[N][N];
        cur = new int[N][N];
        trees = new ArrayDeque[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = sc.nextInt();
                cur[i][j] = 5;
                trees[i][j] = new ArrayDeque<>();
            }
        }
        
        for (int i = 0; i < M; i++) {
            int r = sc.nextInt()-1;
            int c = sc.nextInt()-1;
            int age = sc.nextInt();            
            trees[r][c].add(age);
        }

        for (int i = 0; i < K; i++) {
        	springAndSummer();
        	fall();
        	winter();			
		}

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += trees[i][j].size();
            }
        }
        System.out.println(ans);
    }
    
    static void springAndSummer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(trees[i][j].isEmpty()) continue;

                Deque<Integer> nextTrees = new ArrayDeque<>();
                int dead = 0;

                while(!trees[i][j].isEmpty()) {
                    int age = trees[i][j].pollFirst();
                    if(age <= cur[i][j]) {
                        cur[i][j] -= age;
                        nextTrees.addLast(age + 1);
                    } else {
                        dead += age / 2;
                    }
                }
                trees[i][j] = nextTrees;
                cur[i][j] += dead;
            }
        }
    }

    static void fall() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int age : trees[i][j]) {
                    if (age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int nr = i + dr[d], nc = j + dc[d];
                            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                                trees[nr][nc].addFirst(1);
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cur[i][j] += A[i][j];
            }
        }
    }
}