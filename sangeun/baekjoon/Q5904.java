import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q5904 {

    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
    	long N = sc.nextLong();
    	
    	//리스트에 길이 저장
    	List<Long> list = new ArrayList<>();
    	
        list.add(3L); // moo 길이 3
        int k = 0; //S(0)
        
        //N이 포함되는 S(k) 찾기
        while (list.get(k) < N) {
        	k++; // 다음 S(k)로 이동
        	long len = list.get(k-1) * 2 + (k+3); //앞 뒤 같으니까 앞쪽 길이 * 2 + 가운데 길이
        	list.add(len);
        }
    			
        // S(k) 안에서 N번째 문자 찾기
        while (k > 0) {
            long front = list.get(k - 1); // 앞쪽 길이
            long mid = k + 3;         // 가운데 길이

            // 1. 앞에 있는 경우 -> S(k-1)로 내려가기
            if (N <= front) {
                k--;
            }
            // 2. 가운데에 있는 경우 -> 답 찾기!
            else if (N <= front + mid) {
            	//가운데 첫글자면 m, 나머지는 o
                if (N == front + 1) {
                    System.out.println('m'); 
                } else {
                    System.out.println('o'); 
                }
                return;
            }
            // 3. 뒤에 있는 경우
            else {
                N = N - front - mid; //앞 + 가운데 길이를 빼서 S(k-1) 기준 위치로 
                k--;
            }
        }
        // k=0 -> S(0) = moo
        if (N == 1) {
            System.out.println('m');
        } else {
            System.out.println('o');
        }
    }
}