import java.util.*;

public class Q1759 {
    static int n, m;
    static String[] arr;
    static char[] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new String[m];
        result = new char[n];
        
        for (int i = 0; i < m; i++) {
			arr[i] = sc.next();
		}
        
        Arrays.sort(arr);
        
        password(0,0,0,0);
    }
    
    static void password(int depth, int start, int v, int c) {
    	if(c==n || v>n-2) {
    		return;
    	}
        if (depth == n) {
            if (v >= 1 && c >= 2) {
                System.out.println(new String(result));
            }
            return;
        }

        for (int i = start; i < m; i++) {
            result[depth] = arr[i].charAt(0);
            
            if ("aeiou".contains(arr[i])) {
                password(depth + 1, i + 1, v + 1, c);
            } else {
                password(depth + 1, i + 1, v, c + 1);
            }
        }
    }
}