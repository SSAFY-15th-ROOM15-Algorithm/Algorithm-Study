package codetree;
import java.util.*;
import java.io.*;

public class Q2630 {
	static int white = 0;
	static int blue = 0;
	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(0, 0, N);

		System.out.println(white);
		System.out.println(blue);
	}

	static void solve(int r, int c, int size) {
		int color = board[r][c];
		boolean same = true;

		// 현재 영역이 모두 같은 색인지 확인
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if (board[i][j] != color) {
					same = false;
					break;
				}
			}
			if (!same)
				break;
		}

		if (same) {
			if (color == 0)
				white++;
			else
				blue++;
			return; // 같은 색이면 여기서 끝
		}

		// 색이 다르면 4등분
		int n = size / 2;
		solve(r, c, n); // 좌상
		solve(r, c + n, n); // 우상
		solve(r + n, c, n); // 좌하
		solve(r + n, c + n, n); // 우하
	}
}