package SWEA;

import java.util.Scanner;

public class 햄버거다이어트 {
	static int N, L;
	static int[] score;
	static int[] kcal;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			
			N = sc.nextInt();
			L = sc.nextInt();
			
			score = new int[N]; // 재료별 선호점수
			kcal = new int[N];  // 재료별 칼로리
			
			
			for(int n=0; n<N; n++) {
				score[n] = sc.nextInt();
				kcal[n] = sc.nextInt();
			}
			
			
			// 조합 뽑기	
			max = 0;
			makeBurger(0, 0, 0);
		
			// 출력
			System.out.println("#" + tc + " " + max);
		}
	}
	
	
	//idx부터 뽑는다 / 현재 점수(선호도) / 현재 칼로리
	static void makeBurger(int idx, int curScore, int curKcal) {
		// 백트래킹(가지치기) -> 칼로리 넘으면 리턴
		if(curKcal > L) return;
		
		
		// 기저 조건 -> 모든 재료 확인하고 나면 score랑 max값 비교해서 갱신
		if(idx == N) {
			max = Math.max(max, curScore);
			return;
		}
		
		
		int nextScore = curScore + score[idx];
		int nextKcal = curKcal + kcal[idx];
		
	
		// 현재 재료를 넣는다
		makeBurger(idx+1, nextScore, nextKcal);
		// 현재 재료를 넣지 않는다
		makeBurger(idx+1, curScore, curKcal);
	}
	
	
}
