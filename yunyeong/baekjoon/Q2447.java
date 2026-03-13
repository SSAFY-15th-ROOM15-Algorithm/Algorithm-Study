package baekjoon;

import java.util.Scanner;

public class Q2447 {
	
	static int N;
	static char[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new char[N][N];
			
		// 재귀함수 호출
		draw(0, 0, N, false);
		
		// 출력
		StringBuilder sb = new StringBuilder(); // 시간 초과 안나게 스트링빌더 사용해서 한번에 출력
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);		
	}
	
	
	static void draw(int x, int y, int n, boolean blank) {
		// 기저조건
		// 5번째 칸만 blank=true로 켜져서 ' ' 입력
		if (blank) {
			for (int i = x; i < x + n; i++) {
                for (int j = y; j < y + n; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
		}
		// 나머지는 계속 쪼개다가 더이상 쪼갤 수 없으면 별
		if (n == 1) {
			map[x][y] = '*';
			return;
		}
		
		// 재귀부분
		int nextN = n/3;
		int count = 0;
		
		for(int i=x; i<x+n; i+=nextN) {
			for (int j=y; j<y+n; j+=nextN) {
				count++;
				if (count == 5) {  // 9조각중 5번째 조각이면 걍 통째로 건너뛰어라 -> 공백 유지
					draw(i, j, nextN, true);
				} else {
					draw(i, j, nextN, false);  // 5번째 조각 아니면 또 쪼개서 봐야지 1개짜리가 될때까지!!
				}
				
			}
		}
	}
}
