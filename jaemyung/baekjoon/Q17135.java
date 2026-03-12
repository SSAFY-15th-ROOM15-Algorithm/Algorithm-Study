package asd;
import java.util.*;

public class Q17135 {
	static int n, m, max, range;
	static int[][] map;
	static ArrayList<Integer> archers = new ArrayList<>(); // 궁수의 위치 (col) 담을 ArrayList

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		range = sc.nextInt();
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		max = 0;
		combinations(0, 0);
		System.out.println(max);
	}

	static void combinations(int depth, int start) {
		if (depth == 3) {
			game();
			return;
		}
		for (int i = start; i < m; i++) {
			archers.add(i);
			combinations(depth + 1, i + 1);
			archers.remove(archers.size() - 1);
		}
	}

	static void game() {
		int[][] copyMap = new int[n][m]; // 기존 map을 수정하면 시뮬레이션 한 번 돌린 후 전부 0으로 바뀌기 때문에 깊은 복사
		for (int i = 0; i < n; i++) {
			copyMap[i] = map[i].clone();
		}

		int cnt = 0;
		for (int turn = 0; turn < n; turn++) { // 열 개수만큼 턴 반복
			List<int[]> targets = new ArrayList<>(); // 턴 마다 궁수의 타겟 변경

			for (int j = 0; j < 3; j++) { // 궁수 3명 타겟 설정
				int minD = range + 1;
				int r = -1, c = -1;

				for (int dj = 0; dj < m; dj++) { // 왼쪽 배치된 적 우선타겟이라 열이 아닌 행부터 루프 설정
					for (int di = n - 1; di >= 0; di--) {
						if (copyMap[di][dj] == 1) {
							int dist = Math.abs(di - n) + Math.abs(dj - archers.get(j));
							if (dist <= range && dist < minD) {
								minD = dist;
								r = di;
								c = dj;
							}
						}
					}
				}
				if (r != -1) {
					targets.add(new int[] { r, c });					
				}
			}

			for (int[] t : targets) { // 타겟 좌표 1에서 0으로 바꾸기
				if (copyMap[t[0]][t[1]] == 1) { // 중복 타겟 방지
					copyMap[t[0]][t[1]] = 0;
					cnt++;
				}
			}

			for (int i = n - 1; i > turn; i--) { // 아래에서 위 열 순서대로 복사 (적들 전진), 턴이 지날수록 전진 안 해도 무방
			    copyMap[i] = copyMap[i - 1];
			}
			copyMap[turn] = new int[m]; // 이번 턴에 비워진 줄 처리
		}
		max = Math.max(cnt, max);
	}
}