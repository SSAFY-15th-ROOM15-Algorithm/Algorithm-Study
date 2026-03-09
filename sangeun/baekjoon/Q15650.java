import java.util.Scanner;

public class Q15650 {
	
	static int[] ans;
	static int N, M;

	public static void dfs(int start, int depth) {
		if(depth == M) { 
			for(int i=0; i<M; i++) {
				System.out.print(ans[i] + " "); 
			}
			System.out.println();
			return;
		}
		
		for(int i= start; i<=N; i++) {
			ans[depth] = i;
			dfs(i+1, depth+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		ans = new int[M];
		
		dfs(1,0);
	}
}

