import java.util.Scanner;

public class Q1210 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int t = 1; t <= 10; t++){
            int num = sc.nextInt();
            int N = 100;
            int [][] ladder = new int[N][N];
            int x = 0;
            int y = 0;

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    ladder[i][j] = sc.nextInt();

                    if(ladder[i][j] == 2){
                        x = i;
                        y = j;
                    }
                }
            }

            while (x > 0){
                // 좌
                if(y > 0 && ladder[x][y - 1] == 1) {
                    while (y > 0 && ladder[x][y - 1] == 1) y--;
                }
                // 우 (왼쪽으로 갈 수 없다면)
                else if(y < N - 1 && ladder[x][y + 1] == 1){
                    while (y < N - 1 && ladder[x][y + 1] == 1) y++;
                }
                x--;
            }

            System.out.println("#" + num + " " + y);
        }
    }
}
