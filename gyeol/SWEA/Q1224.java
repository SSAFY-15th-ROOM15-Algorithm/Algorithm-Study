import java.util.*;

public class Q1224 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int t = 1; t <= 10; t++){
            int len = sc.nextInt();
            String str = sc.next();

            // 후위
            String postfix = "";
            Stack<Character> stack = new Stack<>();

            for(int i = 0; i < len; i++){
                char c = str.charAt(i);

                if(c >= '0'&& c <= '9'){
                    postfix += c;
                }
                else if(c == '('){
                    stack.push(c);
                }
                else if(c == ')'){
                    while (stack.peek() != '('){  // '('를 만날 때까지 pop해서 postfix에 추가
                        postfix += stack.pop();
                    }
                    stack.pop(); // '(' 자체는 postfix에 넣지 않고 제거
                }
                else if(c == '*'){
                    while (!stack.isEmpty() && stack.peek() == '*'){
                        postfix += stack.pop();
                    }
                    stack.push(c);
                }
                else if(c == '+'){
                    while (!stack.isEmpty() && stack.peek() != '('){
                        postfix += stack.pop();
                    }
                    stack.push(c);
                }
            }
            while(!stack.isEmpty()){
                postfix += stack.pop();
            }

            // 계산
            Stack<Integer> calc = new Stack<>();
            for(int i = 0; i < postfix.length(); i++){
                char c = postfix.charAt(i);

                if(c >= '0' && c <= '9'){
                    calc.push(c - '0');
                }
                else {
                    if(c == '*'){
                        int num1 = calc.pop();
                        int num2 = calc.pop();

                        calc.push(num1 * num2);
                    }
                    else if(c == '+'){
                        int num1 = calc.pop();
                        int num2 = calc.pop();

                        calc.push(num1 + num2);
                    }
                }
            }
            int ans = calc.pop();
            System.out.println(postfix);
            System.out.println("#" + t + " " + ans);
        }
    }
}
