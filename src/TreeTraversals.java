import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeTraversals {
    private static int N;
    private static int[] postorder;
    private static int[] inorder;
    private static List<Node> ans = new ArrayList<>();

    private static class Node implements Comparable<Node> {
        private int index;
        private int value;

        @Override
        public int compareTo(Node another) {
            if (index < another.index) {
                return -1;
            } else if (index > another.index) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private static void preorder(int root, int start, int end, int index) {
        if (start > end) {
            return;
        }

        int rootIndexInorder = start;
        for (int i = start; i <= end; i++) {
            if (inorder[i] == postorder[root]) {
                rootIndexInorder = i;
                break;
            }
        }

        Node node = new Node();
        node.index = index;
        node.value = postorder[root];
        ans.add(node);
        preorder(root - end + rootIndexInorder - 1, start, rootIndexInorder - 1, 2 * index + 1);
        preorder(root - 1, rootIndexInorder + 1, end, 2 * index + 2);
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        N = Read.nextInt();
        postorder = new int[N];
        inorder = new int[N];
        for (int i = 0; i < postorder.length; i++) {
            postorder[i] = Read.nextInt();
        }
        for (int i = 0; i < inorder.length; i++) {
            inorder[i] = Read.nextInt();
        }
        preorder(N - 1, 0, N - 1, 0);
        Collections.sort(ans);
        System.out.print(ans.get(0).value);
        for (int i = 1; i < ans.size(); i++) {
            System.out.print(" " + ans.get(i).value);
        }
    }
}
