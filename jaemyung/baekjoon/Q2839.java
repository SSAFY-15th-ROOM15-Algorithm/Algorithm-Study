import java.util.Scanner;

public class Q2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt = 0;

        while (true) {
            if (n % 5 == 0) {
                cnt += n / 5;
                System.out.println(cnt);
                break;
            }

            n -= 3;
            cnt++;

            if (n < 0) {
                System.out.println(-1);
                break;
            }
        }
    }
}