import java.util.*;

public class Q17406 {
    static int n, m, k, min = Integer.MAX_VALUE;
    static int[][] grid, rotateIdx;
    static int[] order;
    static boolean[] used;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        grid = new int[n][m];
        rotateIdx = new int[k][3];
        order = new int[k];
        used = new boolean[k];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
            	grid[i][j] = sc.nextInt();
            }

        for (int i = 0; i < k; i++) {
            rotateIdx[i][0] = sc.nextInt() - 1;
            rotateIdx[i][1] = sc.nextInt() - 1;
            rotateIdx[i][2] = sc.nextInt();
        }

        backtrack(0);
        System.out.println(min);
    }

    static void backtrack(int depth) {
        if (depth == k) {
            int[][] copy = new int[n][m];
            for (int i = 0; i < n; i++) {
            	copy[i] = grid[i].clone();
            }

            for (int idx : order) {
            	rotate(copy, rotateIdx[idx]);
            }

            for (int[] row : copy) {
                int sum = 0;
                for (int val : row) sum += val;
                min = Math.min(min, sum);
            }
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!used[i]) {
                used[i] = true;
                order[depth] = i;
                backtrack(depth + 1);
                used[i] = false;
            }
        }
    }

    static void rotate(int[][] arr, int[] info) {
        int r = info[0];
        int c = info[1];
        int s = info[2];
        
        for (int i = 1; i <= s; i++) {
            int rStart = r - i;
            int rEnd = r + i;
            int cStart = c - i;
    		int cEnd = c + i;

            int temp = arr[rStart][cStart];

            for (int row = rStart; row < rEnd; row++) {
            	arr[row][cStart] = arr[row + 1][cStart];
            }
            for (int col = cStart; col < cEnd; col++) {
            	arr[rEnd][col] = arr[rEnd][col + 1];
            }
            for (int row = rEnd; row > rStart; row--) {
            	arr[row][cEnd] = arr[row - 1][cEnd];
            }
            for (int col = cEnd; col > cStart + 1; col--) {
            	arr[rStart][col] = arr[rStart][col - 1];
            }
            
            arr[rStart][cStart + 1] = temp;
        }
    }
}