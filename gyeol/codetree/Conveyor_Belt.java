import java.util.*;

public class Conveyor_Belt {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int T = sc.nextInt();
		
		int[] top = new int[N];
		int[] bottom = new int[N];
		
		for (int i = 0; i < N; i++) {
			top[i] = sc.nextInt();
		}
		for (int i = 0; i < N; i++) {
			bottom[i] = sc.nextInt();
		}
		
		for (int t = 0; t < T; t++) {
			int tempTop = top[N-1];
			int tempBottom = bottom[N-1];
			
			for(int i = N - 1; i >= 1; i--) {
				top[i] = top[i - 1];
			}
			for(int i = N - 1; i >= 1; i--) {
				bottom[i] = bottom[i - 1];
			}
			top[0] = tempBottom;
			bottom[0] = tempTop;
		}
		
		for(int n : top) {
			System.out.print(n + " ");
		}
		System.out.println();
		for(int n : bottom) {
			System.out.print(n + " ");
		}
	}
}