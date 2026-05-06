import java.util.*;

public class Q1232 {
	static int N;
	static String [] tree;
	static int [] left;
	static int [] right;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(int t = 1; t <= 10; t++) {
			N = sc.nextInt();
			tree = new String[N + 1];
			left = new int[N + 1];
			right = new int[N + 1];
			
			for(int i = 0; i < N; i++) {
				int num = sc.nextInt();
				String data = sc.next();
				tree[num] = data;
				
				if(data.equals("+") || data.equals("-") || data.equals("*") || data.equals("/")) {
					left[num] = sc.nextInt();
					right[num] = sc.nextInt();
				}
			}
			
			System.out.println("#" + t + " " + (int)cal(1));
		}
	}
	private static int cal(int v) {
		if(Character.isDigit(tree[v].charAt(0))) return Integer.parseInt(tree[v]);
		
		int L = cal(left[v]);
		int R = cal(right[v]);
		
		switch(tree[v]) {
		case "+" : return L + R;
		case "-" : return L - R;
		case "*" : return L * R;
		case "/" : return L / R;
		}
		return 0;
	}
}
