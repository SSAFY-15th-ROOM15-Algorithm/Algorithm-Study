import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Q1647 {
    
    static int[] parent;
    
    // 각 집이 속한 그룹의 루트 찾기
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    // 두 집이 같은 그룹이면 false (사이클 발생)
    // 다른 그룹이면 합치고 true 반환
    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false; // 이미 같은 그룹
        parent[pa] = pb;
        return true;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt(); // 집의 개수
        int M = sc.nextInt(); // 길의 개수
        
        // M개 길의 간선 정보 저장
        int[][] edges = new int[M][3];
        
        for (int i = 0; i < M; i++) {
            edges[i][0] = sc.nextInt(); // 집 1
            edges[i][1] = sc.nextInt(); // 집 2
            edges[i][2] = sc.nextInt(); // 유지비
        }

        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[2] - b[2]; 
            }
        });
        
//        
//        // 유지비 오름차순 정렬 -> 총 비용을 최소로 만들기 위해 유지비 작은 길부터 선택
//        for(int i = 0; i < M-1; i++) {
//        	for(int j= i+1; j < M; j++) {
//        		if(edges[i][2] > edges[j][2]) { //앞 간선이 더 크면 자리 바꾸기
//        			int temp0 = edges[i][0];
//        			int temp1 = edges[i][1];
//        			int temp2 = edges[i][2];
//        			
//        			edges[i][0] = edges[j][0];
//        			edges[i][1] = edges[j][1];
//        			edges[i][2] = edges[j][2];
//        			
//        			edges[j][0] = temp0;
//        			edges[j][1] = temp1;
//        			edges[j][2] = temp2;
//        		}
//        	}
//        }
        
        
        //parent 배열 초기화 (각 집의 부모를 자기자신으로 설정해 독립된 그룹으로 시작)
        parent = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        
        int totalCost = 0;   // 전체 비용
        int maxEdge = 0;     // 가장 큰 간선
        int edgeCount = 0;   // 선택된 간선 개수
        
        for (int i = 0; i < M; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            int cost = edges[i][2];
            
            // 반환값이 true면
            if (union(a, b)) {
                totalCost += cost;
                maxEdge = cost; // 정렬해서 뒤로 갈수록 커지니까 마지막이 최대
                edgeCount++;
                
                // 간선의 수 N-1 MST 완성 -> break
                if (edgeCount == N - 1) break;
            }
        }
        
        // 가장 큰 간선 제거해서 두 마을로 분리
        System.out.println(totalCost - maxEdge);
        
    }
}