package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Q2263 {
    // O(1)로 현재 mid의 index를 찾기 위해 hashmap 활용
    private static final Map<Integer, Integer> inorderMap = new HashMap<>();
    private static final Deque<Integer> postorder = new ArrayDeque<>();
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());
            inorderMap.put(now, i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postorder.push(Integer.parseInt(st.nextToken()));
        }

        Node root = buildTree(0, N - 1);
        preOrder(root);
        System.out.println(sb);
    }

    private static Node buildTree(int l, int r) {
        if (l > r) {
            return null;
        }

        int now = postorder.pop();
        Node mid = new Node(now);
        int midIdx = inorderMap.get(now);
        mid.right = buildTree(midIdx + 1, r);
        mid.left = buildTree(l, midIdx - 1);

        return mid;
    }

    private static void preOrder(Node root) {
        if (root == null) {
            return;
        }

        sb.append(root.value).append(" ");
        preOrder(root.left);
        preOrder(root.right);
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
}