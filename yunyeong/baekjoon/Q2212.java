package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/*
    집중국의 수신 가능 영역 = 집중국 ~ 가까운 센서 ""사이 거리""의 합
    			   = 센서 간 거리를 다 더하다가 집중국이 사이사이를 끊어주는 역할
    			   = 집중국이 2개면 ""사이 거리"" 하나를 없앨 수 있음!
    			   = 집중국이 k개면 k-1개의 거리를 없앨 수 있다
    			   = 그럼 가장 긴 거리부터 순서대로 잘라주면 됨
 */

public class Q2212 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 센서 개수 (1~10000)
		int k = sc.nextInt(); // 집중국 개수 (1~1000)
		int[] arr = new int[n];
		
		// 근데 n>k 라는 조건이 없으므로 n<=k일수도 있음. 그러면 그냥 0이넹?
		int result = 0;
		if (n<=k) System.out.println(result);
		else {
			for (int i=0; i<n; i++) { // 센서 위치 배열로 받기
				arr[i] = sc.nextInt();
			}
			
			Arrays.sort(arr);  // 순서대로 정렬해둠. 거리 계산하기 위해서
			
			int[] distance = new int[n-1];  // 센서 사이 거리를 넣어둘 배열
			
			for (int i=0; i<n-1; i++) {
				distance[i] = arr[i+1] - arr[i];
			}
			
			Arrays.sort(distance);  // 정렬해놓음. 큰 것부터 지울거라
			
			for (int i=1; i<k; i++) { // (k-1)번 수행
				distance[distance.length-i] = 0;  // 젤 큰 거리부터 무효화
			}
			
			for (int i=0; i<n-1; i++) {
				result += distance[i];  // 남은 사이거리 다 더하면 그게 집중국의 수신가능영역
			}
			
			System.out.println(result);
		}
	}
}
