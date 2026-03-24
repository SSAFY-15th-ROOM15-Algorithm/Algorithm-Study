import java.util.*;

public class Q17281 {
    static int N;
    static int[][] players;
    static int[] order = new int[10];
    static boolean[] selected = new boolean[10];
    static int maxScore = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        players = new int[N][10];
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= 9; j++) {
            	players[i][j] = sc.nextInt();
            }
        }

        order[4] = 1;
        selected[1] = true;

        solve(1);
        System.out.println(maxScore);
    }

    static void solve(int num) {
        if (num == 10) {
            playBall();
            return;
        }

        if (num == 4) {
            solve(num + 1);
            return;
        }

        for (int i = 2; i <= 9; i++) {
            if (!selected[i]) {
                selected[i] = true;
                order[num] = i;
                solve(num + 1);
                selected[i] = false;
            }
        }
    }

    static void playBall() {
        int score = 0;
        int cur = 1;

        for (int i = 0; i < N; i++) {
            int out = 0;

            boolean b1 = false, b2 = false, b3 = false;

            while (out < 3) {
                int result = players[i][order[cur]];

                if (result == 0) {
                	out++;
                }
                else if (result == 1) {
                    if (b3) {
                    	score++;
                    }
                	b3 = b2;
                	b2 = b1;
                	b1 = true;
                } else if (result == 2) {
                    if (b3) {
                    	score++;
                    }
                    if (b2) {
                    	score++;
                    }
                    b3 = b1;
                    b2 = true;
                    b1 = false;
                } else if (result == 3) {
                    if (b3) {
                    	score++; 
                    }
                    if (b2) {
                    	score++; 
                    }
                    if (b1) {
                    	score++;
                    }
                    b3 = true;
                    b2 = false;
                    b1 = false;
                } else if (result == 4) {
                    score += (b1 ? 1 : 0) + (b2 ? 1 : 0) + (b3 ? 1 : 0) + 1;
                    b1 = b2 = b3 = false;
                }

                cur = (cur % 9) + 1;
            }
        }
        maxScore = Math.max(maxScore, score);
    }
}