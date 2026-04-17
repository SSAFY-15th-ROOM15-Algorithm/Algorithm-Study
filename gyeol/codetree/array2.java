import java.util.*;

public class array2 {
	static long N, K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		long left = 1;
		long right = N * N;
		long ans = N * N;
		
		while(left <= right) {
			long mid = (left + right) / 2;
			
			if(isOK(mid)) {
				right = mid - 1;
				ans = Math.min(mid, ans);
			}
			else left = mid + 1;
		}
		
		System.out.println(ans);
	}
	
	private static boolean isOK(long mid) {
		long cnt = 0;
		
		for(int i = 1; i <= N; i++) {
			cnt += Math.min(mid/ i, N);
		}
		
		return cnt >= K;
	}
}
