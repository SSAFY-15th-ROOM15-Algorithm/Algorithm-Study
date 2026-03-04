import java.util.Arrays;
import java.util.Scanner;

public class Q2839 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		int N = sc.nextInt(); //설탕 무게
		
		int ans = 0; //봉지의 개수
		
		//5로 나눠지면 5kg 사용, 아니면 3kg 하나씩 줄이며 확인
		while(N >=0) {
			if(N % 5 == 0) {	
				ans += N/5;
				System.out.println(ans);
				return;
			}
			N-=3;
			ans++;
		}
		System.out.println(-1);
	}
}
