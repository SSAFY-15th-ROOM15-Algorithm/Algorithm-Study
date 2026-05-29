import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class 단어가등장하는횟수 {
    public static void main(String[] args) throws IOException {
        // 빠른 입출력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            String B = br.readLine().trim();
            String S = br.readLine().trim();
            
            int answer = kmp(B, S);
            
            System.out.println("#" + tc + " " + answer);
        }
    }
    
    // KMP 알고리즘 구현 함수
    public static int kmp(String text, String pattern) {
        int[] pi = getPi(pattern);
        int textLen = text.length();
        int patternLen = pattern.length();
        
        int count = 0;
        int j = 0; // 패턴을 가리키는 인덱스
        
        for (int i = 0; i < textLen; i++) {
            // 본문과 패턴이 일치하지 않으면, pi 배열을 이용해 j를 이동
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            
            // 본문과 패턴이 일치하는 경우
            if (text.charAt(i) == pattern.charAt(j)) {
                // 패턴의 끝까지 모두 일치한 경우
                if (j == patternLen - 1) {
                    count++; // 등장 횟수 증가
                    
                    // 문제 조건: "중첩될 수 있음" (예: ABABA에서 ABA는 2번)
                    // 패턴을 찾은 후에도 다음 중첩 매칭을 위해 j를 pi[j]로 되돌림
                    j = pi[j]; 
                } else {
                    j++;
                }
            }
        }
        
        return count;
    }
    
    // 부분 일치 테이블(Failure Function) 생성 함수
    public static int[] getPi(String pattern) {
        int len = pattern.length();
        int[] pi = new int[len];
        int j = 0;
        
        for (int i = 1; i < len; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        
        return pi;
    }
}