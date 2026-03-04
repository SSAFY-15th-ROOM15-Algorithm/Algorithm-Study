import java.util.*;

public class Q2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int bag = 0;

        while (N >= 0){
            if(N % 5 == 0){
                System.out.print(N / 5 + bag);
                return;
            }
            N -= 3;
            bag++;
        }
        System.out.print(-1);
    }
}