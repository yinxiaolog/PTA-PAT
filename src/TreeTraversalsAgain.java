import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeTraversalsAgain {
    private static List<Integer> preOrder = new ArrayList<>();
    private static List<Integer> inOrder = new ArrayList<>();
    private static List<Integer> postOrder = new ArrayList<>();

    private static int pos;

    private static int findRootIndex(int left, int right, int root) {
        for (int i = left; i < right; i++) {
            if (inOrder.get(i) == root) {
                return i;
            }
        }
        return -1;
    }

    private static void rec(int left, int right) {
        if (left >= right) {
            return;
        }

        int root = preOrder.get(pos++);

        int index = findRootIndex(left, right, root);
        rec(left, index);
        rec(index + 1, right);
        postOrder.add(root);
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N * 2; i++) {
            String operation = Read.next();
            if (operation.equals("Push")) {
                int x = Read.nextInt();
                stack.push(x);
                preOrder.add(x);
            }

            if (operation.equals("Pop")) {
                inOrder.add(stack.pop());
            }
        }

        pos = 0;
        rec(0, preOrder.size());
        System.out.println(postOrder.toString().replaceAll("[\\[\\],]", ""));
    }
}
