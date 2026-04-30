import java.util.Scanner;

public class Q3289 {
	static int N, M;
	static int [] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 1 ~ N개의 집합
			M = sc.nextInt(); 
			
			p = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				p[i] = i;
			}
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < M; i++) {
				int state = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				if(state == 0) { // 합집합
					int pa = findSet(a);
					int pb = findSet(b);
					
					if(pa != pb) p[pb] = pa;
				}
				else if(state == 1) { // 집합에 포함되어있는지 확인
					if(findSet(a) == findSet(b)) {
						sb.append(1);
					}
					else sb.append(0);
				}
			}
			System.out.println("#" + t + " " + sb.toString());
		}
	}
	private static int findSet(int x) {
		if(x != p[x]) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}
}
