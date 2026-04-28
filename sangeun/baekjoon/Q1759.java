import java.util.Arrays;
import java.util.Scanner;

public class Q1759 {

    public static int L, C;
    public static char[] list;  // 입력받은 알파벳 목록
    public static char[] pw;    // 선택한 암호

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();  // 암호 길이
        C = sc.nextInt();  // 알파벳 개수

        list = new char[C];
        pw = new char[L];

        // 알파벳 입력
        for (int i = 0; i < C; i++) {
            list[i] = sc.next().charAt(0);
        }

        //사전순
        Arrays.sort(list);

        dfs(0, 0);
    }

    public static void dfs(int start, int depth) {

        // L개를 다 골랐을 때
        if (depth == L) {
            int mo = 0;  // 모음
            int ja = 0;  // 자음

            for (int i = 0; i < L; i++) {
                if (pw[i] == 'a' || pw[i] == 'e' || pw[i] == 'i' || pw[i] == 'o' || pw[i] == 'u') {
                    mo++;
                } else {
                    ja++;
                }
            }

            // 모음 최소 1개, 자음 최소 2개인지 확인
            if (mo >= 1 && ja >= 2) {
                System.out.println(pw);
            }
            return;
        }

        // start부터 끝까지 하나씩 선택해서 조합 만들기
        for (int i = start; i < C; i++) {
            pw[depth] = list[i];
            // 다음은 현재보다 뒤에서
            dfs(i + 1, depth + 1);
        }
    }
}