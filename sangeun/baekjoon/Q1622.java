import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			//다음값이 없으면 종료
			if(!sc.hasNextLine()) break;
			String a = sc.nextLine();
			
			if(!sc.hasNextLine()) break;
			String b = sc.nextLine();
			
			int[] arr1 = new int[26];
			int[] arr2 = new int[26];
			
			//a 문자 개수
			for(int i=0; i<a.length(); i++) {
				char ch = a.charAt(i);
				arr1[ch-'a']++;
			}
			
			//b 문자 개수
			for(int i=0; i<b.length(); i++) {
				char ch = b.charAt(i);
				arr2[ch-'a']++;
			}
			//공통문자 세기
			for(int i=0; i<26; i++) {
				// a: (1,2) -> 1개 공통
				int cnt = Math.min(arr1[i], arr2[i]);
				
				for(int j=0; j<cnt; j++) {
					System.out.print((char)('a'+ i));
				}
			}
			System.out.println();
		}	
	}
}