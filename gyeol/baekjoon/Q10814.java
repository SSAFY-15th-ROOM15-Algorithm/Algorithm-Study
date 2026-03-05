import java.util.*;

public class Q10814 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기
        
        String[] arr = new String[N];
        
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextLine();
        }
        
        Arrays.sort(arr, (a, b) -> {
            // 1. 공백 기준 첫 번째 요소(나이) 추출 및 정수 변환
            int ageA = Integer.parseInt(a.split(" ")[0]);
            int ageB = Integer.parseInt(b.split(" ")[0]);
            
            // 2. 나이 오름차순 정렬
            // 3. 나이가 같을 경우 입력 순서를 유지(Stable Sort 특성 활용)
            return ageA - ageB;
        });
        
        for(String s : arr) {
            System.out.println(s);
        }
    }
}