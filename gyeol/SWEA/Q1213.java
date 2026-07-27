import java.util.*;

public class Q1213 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int t = 1; t <= 10; t++){
            int T = sc.nextInt();
            String word = sc.next();
            String str = sc.next();

            int ans = 0;
            for(int i = 0; i <= str.length() - word.length(); i++){
                boolean find = true;

                for(int j = 0; j < word.length(); j++){
                    if(str.charAt(i + j) != word.charAt(j)){
                        find = false;
                        continue;
                    }
                }
                if(find) ans++;
            }
            System.out.println("#" + " " + T + " " + ans);
        }
    }
}
