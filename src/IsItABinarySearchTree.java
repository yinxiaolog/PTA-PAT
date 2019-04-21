import util.Read;

import java.io.IOException;
import java.util.Stack;

public class IsItABinarySearchTree {
    private static int[] preOrder;
    private static boolean isMirror = false;
    private static Stack<Integer> postOrder = new Stack<>();

    private static void getPostOrder() {
        getPostOrder(0, preOrder.length - 1);
    }
    private static void getPostOrder(int start, int end) {
        if (start > end) {
            return;
        }

        int i, j;
        if (!isMirror) {
            for (i = start + 1; i <= end; i++) {
                if (preOrder[i] >= preOrder[start]) {
                    break;
                }
            }

            for (j = end; j > start; j--) {
                if (preOrder[j] < preOrder[start]) {
                    break;
                }
            }
        } else {
            for (i = start + 1; i <= end; i++) {
                if (preOrder[i] < preOrder[start]) {
                    break;
                }
            }

            for (j = end; j > start; j--) {
                if (preOrder[j] >= preOrder[start]) {
                    break;
                }
            }
        }

        if (i - j != 1) {
            return;
        }

        getPostOrder(start + 1, j);
        getPostOrder(i, end);
        postOrder.push(preOrder[start]);
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        preOrder = new int[N];
        for (int i = 0; i < N; i++) {
            preOrder[i] = Read.nextInt();
        }

        getPostOrder();
        if (postOrder.size() < N) {
            isMirror = true;
            postOrder.clear();
            getPostOrder();
        }

        if (postOrder.size() == N) {
            System.out.println("YES");
            System.out.print(postOrder.get(0));
            for (int i = 1; i < postOrder.size(); i++) {
                System.out.print(" " + postOrder.get(i));
            }

        } else {
            System.out.println("NO");
        }
    }
}
