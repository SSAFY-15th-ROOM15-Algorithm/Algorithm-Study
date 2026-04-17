import java.util.*;

public class Node {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 노드 수
        String[] nodes = new String[N];
        HashMap<String, Integer> stringToNode = new HashMap<>();

        // 노드 이름 입력
        for (int i = 0; i < N; i++)
            nodes[i] = sc.next();

        // 사전순 정렬
        Arrays.sort(nodes);

        // 이름 -> 번호 매핑
        for (int i = 0; i < N; i++)
            stringToNode.put(nodes[i], i);

        int M = sc.nextInt(); // 관계 수

        // 그래프와 자식 노드 정보 초기화
        List<Integer>[] edges = new ArrayList[N];
        List<Integer>[] child = new ArrayList[N];
        int[] indegree = new int[N];

        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
            child[i] = new ArrayList<>();
        }

        // 조상 관계 입력: x의 조상 중 y가 있음 → y -> x
        for (int i = 0; i < M; i++) {
            String xStr = sc.next();
            String yStr = sc.next();
            
            int x = stringToNode.get(xStr);
            int y = stringToNode.get(yStr);

            edges[y].add(x);  // y -> x
            indegree[x]++;
        }

        // 루트 노드 찾기
        Queue<Integer> q = new LinkedList<>();
        List<Integer> roots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                roots.add(i);
            }
        }

        // 위상정렬로 트리 복원
        while (!q.isEmpty()) {
            int x = q.poll();
            
            for (int y : edges[x]) {
                indegree[y]--;
                
                if (indegree[y] == 0) {
                    q.add(y);
                    child[x].add(y);
                }
            }
        }

        // 자식 노드 사전순 정렬
        for (int i = 0; i < N; i++)
            Collections.sort(child[i]);

        // 출력
        System.out.println(roots.size()); // 로트 노드 수
        for (int i = 0; i < roots.size(); i++)
            System.out.print(nodes[roots.get(i)] + " "); // 루트 노드 이름
        System.out.println();

        for (int i = 0; i < N; i++) {
            System.out.print(nodes[i] + " "); // 부모 노드 사전순으로 1개씩
            System.out.print(child[i].size() + " "); // 자식 노드 수
            
            for (int c : child[i]) // 자식 노드
                System.out.print(nodes[c] + " ");
            System.out.println();
        }
    }
}