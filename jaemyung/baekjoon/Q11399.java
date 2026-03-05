import java.util.Arrays;
import java.util.Scanner;

public class Q11399 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
        Arrays.sort(arr);
        int sum = 0;
        int mult = n;
        for (int i = 0; i < n; i++) {
			sum += arr[i]*mult--;
		}
        System.out.println(sum);
    }
}