import java.util.*;

public class Q4123 {
	static int N, max, min, ans;
	static int [] cal;
	static int [] nums;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			cal = new int[4]; // +, -, *, / 순
			nums = new int[N];
			
			for(int i = 0; i < 4; i++) {
				cal[i] = sc.nextInt();
			}
			
			
			for(int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}
			
			min = Integer.MAX_VALUE;
			max = -Integer.MAX_VALUE;
			ans = 0;
			
			dfs(1, nums[0], cal);
			ans = Math.abs(max - min);
			System.out.println("#" + t + " " + ans);
		}
	}
	private static void dfs(int idx, int num, int [] cal) {
		if(idx == N) {
			min = Math.min(min, num);
			max = Math.max(max, num);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(cal[i] > 0) {
				cal[i]--;
				int next = 0;
				
				switch(i) {
					case 0 : next = num + nums[idx]; break;
					case 1 : next = num - nums[idx]; break;
					case 2 : next = num * nums[idx]; break;
					case 3 : next = num = num / nums[idx]; break;
				}
				
				dfs(idx + 1, next, cal);
				cal[i]++;
			}
		}
	}
}
