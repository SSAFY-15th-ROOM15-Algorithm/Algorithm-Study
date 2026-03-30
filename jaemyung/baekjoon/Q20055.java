import java.util.*;

public class Q20055 {
    static int n, k, cnt;
    static int[] belt;
    static boolean[] robot;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        cnt = 0;
        int res = 0;
        belt = new int[n * 2];
        robot = new boolean[n];

        for (int i = 0; i < n * 2; i++) {
            belt[i] = sc.nextInt();
        }

        while (true) {
            res++;
            rotate();
            robotMove();
            putRobot();

            if (cnt >= k) {
                System.out.println(res);
                return;
            }
        }
    }

    static void rotate() {
        int tmp = belt[n * 2 - 1];
        for (int i = n * 2 - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }
        belt[0] = tmp;

        for (int i = n - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false;
        robot[n - 1] = false;
    }

    static void robotMove() {
        for (int i = n - 2; i >= 0; i--) {
            if (robot[i] && !robot[i + 1] && belt[i + 1] > 0) {
                robot[i] = false;
                robot[i + 1] = true;
                belt[i + 1]--;
                if (belt[i + 1] == 0) cnt++;
            }
        }
        robot[n - 1] = false;
    }

    static void putRobot() {
        if (belt[0] > 0 && !robot[0]) {
            robot[0] = true;
            belt[0]--;
            if (belt[0] == 0) cnt++;
        }
    }
}