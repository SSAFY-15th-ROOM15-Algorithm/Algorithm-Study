import java.util.*;

public class Q1222 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int t = 1; t <= 10; t++){
            int len = sc.nextInt();
            String str = sc.next();

            // 후외
            String postfix = "";
            Stack<Character> stack = new Stack<>();

            for(int i = 0; i < len; i++) {
                char c = str.charAt(i);

                if(c != '+'){
                    postfix += c;
                }
                else{
                    // stack에 연산자가 이미 있다면
                    if(!stack.isEmpty() && stack.peek() == '+'){
                        postfix += stack.pop();
                    }
                    // 현재 '+'를 stack에 넣기
                    stack.push(c);
                }
            }
            while (!stack.isEmpty()){
                postfix += stack.pop();
            }

            // 계산
            Stack<Integer> calc = new Stack<>();
            for(int i = 0; i < postfix.length(); i++){
                char c = postfix.charAt(i);

                if(c != '+'){
                    calc.push(c - '0');
                }
                else{
                    int num1 = calc.pop();
                    int num2 = calc.pop();

                    calc.push(num1 + num2);
                }
            }
            int ans = calc.pop();
            System.out.println("#" + t + " " + ans);
        }
    }
}
