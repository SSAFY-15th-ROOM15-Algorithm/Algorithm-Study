package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Q1759다시 {
	static int L, C;
	static char[] password;
	static char[] input;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();
		C = sc.nextInt();
		
		password = new char[L]; // 암호 뽑아서 넣을 임시배열
		input = new char[C];  // 입력받은 알파벳
		
		for (int i=0; i<C; i++) {
			input[i] = sc.next().charAt(0);
		}
		
		// 알파벳 사전순 정렬
		Arrays.sort(input);
		
		// 재귀함수 호출
		make(0,0);
	}
	
	static void make(int start, int count) {
		// 종료 조건 -> L개를 다 뽑았으면
		if(count == L) {
			// 조건에 맞는지 확인(모음1 자음2)
			if(valid(password)) {
				System.out.println(password); //출력
			}
			return;
		}
		
		// 조합 뽑기 반복문
		for(int i=start; i<C; i++) {
			password[count] = input[i];
			make(i+1, count+1);
		}
	}
	
	// 조건에 맞는지 확인하는 함수(모음1개 자음2개 들어있나)
	static boolean valid(char[] c) {
		int vow = 0;
		int con = 0;
		
		for(char x : c) {
			if(isVow(x)) vow++;
			else con++;
		}
		
		return vow>=1 && con>=2;	
	}
	
	// 모음인지 아닌지
	static boolean isVow(char x) {
		return x=='a' || x=='e' || x=='i' || x=='o' || x=='u';
	}
	
}
