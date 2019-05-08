import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HighestPriceInSupplyChain {
    private static class Node {
        int index;
        List<Integer> leaves = new ArrayList<>();

        public Node(int index) {
            this.index = index;
        }
    }

    private static Node[] tree;
    private static double price;
    private static double rate;
    private static int root;

    private static int maxDepth = 0;
    private static int number = 0;

    private static void dfs(int index, int depth) {
        if (tree[index].leaves.size() == 0) {
            if (depth == maxDepth) {
                number++;
            }
            if (depth > maxDepth) {
                maxDepth = depth;
                number = 1;
            }

            return;
        }

        for (Integer i : tree[index].leaves) {
            dfs(i, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        price = Read.nextDouble();
        rate = Read.nextDouble();
        tree = new Node[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new Node(i);
        }
        rate = 1 + rate / 100;

        for (int i = 0; i < N; i++) {
            int parents = Read.nextInt();
            if (parents != -1) {
                tree[parents].leaves.add(i);
            } else {
                root = i;
            }
        }

        dfs(root, 0);
        System.out.printf("%.2f %d\n", price * Math.pow(rate, maxDepth), number);
    }
}
