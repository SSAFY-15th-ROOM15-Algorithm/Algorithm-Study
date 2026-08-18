import java.util.*;

public class Q1216 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int t = 1; t <= 10; t++){
            int T = sc.nextInt();
            int N = 100;
            char [][] board = new char[N][N];

            for(int i = 0; i < N; i++){
                String str = sc.next();
                for(int j = 0; j < N; j++){
                    board[i][j] = str.charAt(j);
                }
            }

            int max = 0;
            // 가로 줄 탐색
            for(int i = 0; i < N; i++){
                // 회문의 길이 : max보다 긴 길이만 (거꾸로 찾기)
                for(int L = N; L > max; L--){
                    // 회문 시작 위치 설정
                    for(int j = 0; j <= N - L; j++){
                        int left = j;
                        int right = j + L - 1;
                        boolean match = true;

                        while(left < right){
                            // 회문 탐색
                            if(board[i][left] != board[i][right]){
                                match = false;
                                break;
                            }
                            left++;
                            right--;
                        }
                        if(match){
                            max = Math.max(max, L);
                            break;
                        }
                    }
                }
            }

            // 세로 줄 탐색
            for(int i = 0; i < N; i++){
                // 회문의 길이 설정
                for(int L = N; L >= max; L--){
                    // 맨 왼쪽
                    for(int j = 0; j <= N - L; j++){
                        int left = j;
                        int right = j + L - 1;
                        boolean match = true;

                        while (left < right){
                            if(board[left][i] != board[right][i]){
                                match = false;
                                break;
                            }
                        }
                        left++;
                        right--;

                        if(match){
                            max = Math.max(max, L);
                            break;
                        }
                    }
                }
            }

            System.out.println("#" + T + " " + max);

        }
    }
}
