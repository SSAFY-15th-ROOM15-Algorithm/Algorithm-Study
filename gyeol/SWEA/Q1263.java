import java.util.*;

public class Q1263 {
	static final int INF = Integer.MAX_VALUE;
	static int [][] dist;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			
			dist = new int [N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int conn = sc.nextInt(); // 0 : 연결 안됨, 1 : 연결
					
					if(i == j) dist[i][j] = 0;
					else if(conn == 0) dist[i][j] = INF;
					else if(conn == 1) dist[i][j] = conn;
				}
			}
			
			for(int k = 0; k < N; k++) { // 경유지
				for(int i = 0; i < N; i++) { // 출발지
					if(dist[i][k] == INF) continue;
					
					for(int j = 0; j < N; j++) { // 도착지
						if(dist[k][j] == INF) continue;
						
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
			
			int min = INF;
			for(int i = 0; i < N; i++) {
				int sum = 0;
				for(int j = 0; j < N; j++) {
					sum += dist[i][j];
				}
				min = Math.min(min, sum);
			}
			System.out.println("#" + t + " " + min);
		}
	}
}
