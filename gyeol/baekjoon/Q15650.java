import java.util.Scanner;

public class Q15650 {
	static int N, M;
	static int [] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		arr = new int [M];
		
		dfs(0, 1);
	}
	
	private static void dfs(int idx, int start) {
		if(idx == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i <= N; i++) {
			arr[idx] = i;
			dfs(idx + 1, i + 1);
		}
	}
}
