import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class CompleteBinarySearchTree {
    private static int[] inOrder;
    private static int[] levelOrder;
    private static int index = 0;

    private static void levelOrder(int n) {
        if (n >= inOrder.length) {
            return;
        }

        levelOrder(n * 2 + 1);
        levelOrder[n] = inOrder[index++];
        levelOrder(n * 2 + 2);
        //System.out.println(Arrays.toString(levelOrder));
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        inOrder = new int[N];
        levelOrder = new int[N];
        for (int i = 0; i < N; i++) {
            inOrder[i] = Read.nextInt();
        }

        Arrays.sort(inOrder);
        levelOrder(0);
        System.out.println(Arrays.toString(levelOrder).replaceAll("[\\[\\],]", ""));
    }
}
