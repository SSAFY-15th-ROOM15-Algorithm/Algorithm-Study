import java.util.Scanner;

/*
모든 국가가 연결된 연결 그래프에서
모든 노드를 방문하기 위한 최소 간선 수는 N-1
따라서 비행 경로와 관계없이 정답은 항상 N-1
 */

public class Q9372 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=0; tc<T; tc++) {
			int N = sc.nextInt(); //국가의 수
			int M = sc.nextInt(); //비행기의 종류
			
			for(int i=0; i<M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
			}
			System.out.println(N-1);
		}
	}
}
