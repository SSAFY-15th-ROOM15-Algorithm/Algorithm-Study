package asd;
import java.util.*;

public class Q3190 {
    static int n, k, l, dir, cnt;
    static int[][] map;
    static Queue<Integer> turnTime = new LinkedList<>();
    static Queue<Character> turn = new LinkedList<>();
    
    // RDLU
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Deque<int[]> snake = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        k = sc.nextInt();

        map = new int[n][n];
        for (int i = 0; i < k; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            map[r][c] = 1; // 사과 유무
        }

        l = sc.nextInt();
        for (int i = 0; i < l; i++) {
            turnTime.add(sc.nextInt());
            turn.add(sc.next().charAt(0));
        }

        dir = 0;
        cnt = 0;
        snake.addFirst(new int[]{0, 0});

        System.out.println(game());
    }

    static int game() {
        while (true) {
            cnt++;
            int nr = snake.peekFirst()[0] + dr[dir];
            int nc = snake.peekFirst()[1] + dc[dir];

            if (isEnd(nr, nc)) {
                return cnt;
            }

            if (map[nr][nc] == 1) {
                map[nr][nc] = 0;
                snake.addFirst(new int[]{nr, nc});
            } else {
                snake.addFirst(new int[]{nr, nc});
                snake.pollLast();
            }

            if (!turnTime.isEmpty() && turnTime.peek() == cnt) {
                char nextDir = turn.poll();
                turnTime.poll();
                
                if (nextDir == 'D') {
                    dir = (dir + 1) % 4;
                } else {
                    dir = (dir + 3) % 4;
                }
            }
        }
    }

    static boolean isEnd(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return true;
        }

        for (int[] part : snake) {
            if (part[0] == r && part[1] == c) {
                return true;
            }
        }
        return false;
    }
}