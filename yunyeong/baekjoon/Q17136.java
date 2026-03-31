package baekjoon;

import java.util.Scanner;

public class Q17136 {
	static int[][] board = new int[10][10]; // 맵
	static int[] paper = {0, 5, 5, 5, 5, 5}; // 사이즈별 갖고있는 색종이 수
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		// 함수 호출할 부분
		colorPapers(0, 0, 0);
		
		// min이 초깃값이랑 똑같으면 불가능하다는 뜻이니까 -1 출력
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	
	// 맵 탐색할거임 (r, c) 좌표를 탐색한다. cnt는 사용한 색종이 수
	static void colorPapers(int r, int c, int cnt) {
		// 끝까지 탐색했으면 최솟값 갱신
		if (r >= 9 && c > 9) {
			min = Math.min(min, cnt);
			return;
		}
		
		// 가지치기 -> 카운트가 이미 최솟값보다 커졌으면 그만
		if (cnt >= min) return;
		
		// 한줄(행)씩 검사하고 다음 행으로 넘어가깅
		if (c > 9) {
			colorPapers(r+1, 0, cnt);
			return;
		}
		
		// 숫자 1 있는 부분을 가려보자
		if (board[r][c] == 1) {
			for (int size=5; size>=1; size--) {
				if (paper[size] > 0 && canAttach(r, c, size)) { // 붙일 색종이가 남아있어야되고, 붙여도 되는지도 확인해야함
					attach(r, c, size, 0); // 색종이 붙이기
					paper[size]--;
					colorPapers(r, c+1, cnt+1);
					paper[size]++;			// 끝까지 가봤는데 답을 못찾았거나 or 다른 경우의 수도 확인해야되니까 원상복구하고 여기서부터 다시 
					attach(r, c, size, 1); // 색종이 떼기
				}
			}
		} else {
			// 0이면 다음 칸으로 패스
			colorPapers(r, c+1, cnt);
		}		
	}
	
	// 색종이를 붙일 수 있는지 체크하는 함수
	// 지금 이 위치에 이 크기의 색종이를 붙여도 종이 밖으로 안 나가고, 그 영역이 모두 '1'인가?
	static boolean canAttach(int r, int c, int size) {
		if ( r + size > 10 || c + size > 10 ) return false; //색종이가 종이 밖으로 벗어나면 안됨
		for (int i=r; i<r+size; i++) {
			for (int j=c; j<c+size; j++) {
				if (board[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	// 색종이를 붙임(0으로 만듦) 또는 뗌(다시 1로 만듦)
	static void attach(int r, int c, int size, int state) {
		for (int i=r; i<r+size; i++) {
			for (int j=c; j<c+size; j++) {
				board[i][j] = state;
			}
		}
	}
}
