import java.util.*;

public class Q15686 {
    static int n, m;
    static int min = Integer.MAX_VALUE;
    static ArrayList<int[]> houses = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();   
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int xy = sc.nextInt();
				if(xy == 2) {
					chicken.add(new int[] {i,j});
				} else if(xy == 1) {
					houses.add(new int[] {i,j});
				}
			}
		}
        visited = new boolean[chicken.size()];
        
        removeChicken(0,0);
        
        System.out.println(min);
    }
    
    static void removeChicken(int depth, int start) {
    	if (depth == m) {
            int totalDist = 0;

            for (int i = 0; i < houses.size(); i++) {
                int minDist = Integer.MAX_VALUE;
                int hR = houses.get(i)[0];
                int hC = houses.get(i)[1];
                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        int cR = chicken.get(j)[0];
                        int cC = chicken.get(j)[1];
                        int dist = Math.abs(hR - cR) + Math.abs(hC - cC);

                        minDist = Math.min(minDist, dist);
                    }
                }
                totalDist += minDist;
            }
            
            min = Math.min(min, totalDist);
            return;
        }
    	
    	for (int i = start; i < chicken.size(); i++) {
    		visited[i] = true;
			removeChicken(depth+1,i+1);
			visited[i] = false;
		}
    }
}