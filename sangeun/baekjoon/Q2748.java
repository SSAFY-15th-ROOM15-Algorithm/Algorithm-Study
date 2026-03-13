import java.util.Scanner;

public class Q2748 {
	static long[] memo = new long[91];
	
    static long fibo(int n){

        memo[0] = 0;
        memo[1] = 1;

        for(int i = 2; i <= n; i++) {
        	memo[i] = memo[i-1] + memo[i-2];
        }

        return memo[n];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(fibo(n));
    }
}