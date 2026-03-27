package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1759 {
	static int L, C;
    static char[] input;
    static char[] password;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken()); // 암호 자릿수
        C = Integer.parseInt(st.nextToken()); // 후보 문자 종류

        input = new char[C];  // 후보 알파벳 목록
        password = new char[L];  // 정답(이 될 수 있는) 암호를 담을 임시 배열 

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            input[i] = st.nextToken().charAt(0);
        }

        // 1. 사전순 출력을 위해 입력받은 알파벳 정렬
        Arrays.sort(input);

        // 2. 백트래킹 시작 (시작 인덱스: 0, 현재까지 뽑은 개수: 0)
        makePassword(0, 0);
    }

    public static void makePassword(int start, int count) {
        // 암호 길이 L을 채운 경우
        if (count == L) {
            if (isValid()) { // 모음1 자음2 충족하는지 확인하고 맞으면 출력
                System.out.println(new String(password));
            }
            return;
        }

        // 조합 생성
        for (int i=start; i<C; i++) {
            password[count] = input[i];
            makePassword(i + 1, count + 1);
        }
    }

    // 모음 1개, 자음 2개 이상인지 확인하는 메소드
    public static boolean isValid() {
        int vowels = 0;   // 모음
        int consonants = 0; // 자음

        for (char x : password) {
            if (isVowel(x)) vowels++;
            else consonants++;
        }

        return vowels >= 1 && consonants >= 2;
    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
