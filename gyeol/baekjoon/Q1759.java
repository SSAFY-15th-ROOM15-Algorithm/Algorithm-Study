import java.util.*;

public class Q1759 {

    static int L, C;
    static char[] arr;
    static char[] result;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        C = sc.nextInt();

        arr = new char[C];
        result = new char[L];

        for(int i = 0; i < C; i++){
            arr[i] = sc.next().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0,0);
    }

    static void dfs(int idx, int depth){

        if(depth == L){

            int vowel = 0;
            int consonant = 0;

            for(char c : result){
                if(isVowel(c)) vowel++;
                else consonant++;
            }

            if(vowel >= 1 && consonant >= 2){
                System.out.println(new String(result));
            }

            return;
        }

        for(int i = idx; i < C; i++){
            result[depth] = arr[i];
            dfs(i + 1, depth + 1);
        }
    }

    static boolean isVowel(char c){
        return c=='a' || c=='e' || c=='i' || c=='o' || c=='u';
    }
}