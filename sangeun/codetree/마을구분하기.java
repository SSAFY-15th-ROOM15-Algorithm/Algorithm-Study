package practice;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class 마을구분하기 {
    public static int DIR_NUM = 4;
    public static int MAX_NUM = 25;
    
    public static int n;
    public static int[][] grid = new int[MAX_NUM][MAX_NUM];
    public static boolean[][] visited = new boolean[MAX_NUM][MAX_NUM];
    public static ArrayList<Integer> peopleNums = new ArrayList<>();
    public static int peopleNum;
    
    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    
    public static boolean canGo(int x, int y) {
        if(!inRange(x, y))
            return false;
    
        if(visited[x][y] || grid[x][y] == 0)
            return false;
    
        return true;
    }
    
    public static void DFS(int x, int y) {
    	
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
    
        for(int dir = 0; dir < DIR_NUM; dir++) {
            int newX = x + dx[dir];
            int newY = y + dy[dir];
    
            if(canGo(newX, newY)){
                visited[newX][newY] = true;
                peopleNum++;
                DFS(newX, newY);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(canGo(i, j)) {
                	
                    visited[i][j] = true;
                    peopleNum = 1;

                    DFS(i, j);

                    peopleNums.add(peopleNum);
                }
            }
        }

        Collections.sort(peopleNums);

        System.out.println((int) peopleNums.size());
        for(int i = 0; i < (int) peopleNums.size(); i++)
            System.out.println(peopleNums.get(i));
    }
}
