import java.util.*;

public class Q5432 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++){
            String str = sc.next();
            Stack<Character> stick = new Stack<>();
            int total = 0;

            for(int i = 0; i < str.length(); i++){
                char c = str.charAt(i);

                if(c == '('){
                    stick.push(c);
                }
                else if(c == ')'){
                    if(str.charAt(i - 1) == '('){ // 레이저인 경우
                        // 1. 레이저의 '(' 제거
                        stick.pop();
                        // 2. 현재 남아있는 막대기 수만큰 total 증가
                        total += stick.size();
                    }
                    else{ // 쇠막대기로 끝나는 경우
                        // 1. 끝난 막대기 제거
                        stick.pop();
                        // 2. 그 막대기의 마지막 조각 1개를 total에 추가
                        total += 1;
                    }
                }
            }

            System.out.println("#" + t + " " + total);
        }
    }
}
