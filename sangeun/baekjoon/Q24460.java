package 분할정복;

import java.util.Arrays;
import java.util.Scanner;

public class 특별상 {
	static int arr[][];
	
	public static void main(String[] args) {
		 
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
		
		int result = pick(0, 0, N);
		System.out.println(result);
	}
	
	static int pick(int x, int y, int N) {
		if(N == 1) return arr[x][y];
		
		int size = N/2;
		
		int p1 = pick(x, y, size); //왼쪽 위
		int p2 = pick(x+size, y, size); //오른쪽 위
		int p3 = pick(x, y+size, size); // 왼쪽 아래
		int p4 = pick(x+size, y+size, size); //오른쪽 아래
		
		int [] chair = {p1, p2, p3, p4}; 
		Arrays.sort(chair);
		return chair[1]; //4개 중 두번째로 작은 수
	}
}