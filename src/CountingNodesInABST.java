import util.Read;

import java.io.IOException;

public class CountingNodesInABST {
    private static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    private static Node insert(Node root, int key) {
        if (root == null) {
            root = new Node(key, null, null);
        } else if (key <= root.key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    private static int maxLevel = -1;
    private static int[] level = new int[1000];

    private static void dfs(Node root, int depth) {
        if (root == null) {
            maxLevel = Math.max(depth, maxLevel);
            return;
        }

        level[depth]++;
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        Node root = null;

        if (N == 1) {
            System.out.println("1 + 0 = 1");
            return;
        }
        for (int i = 0; i < N; i++) {
            int key = Read.nextInt();
            root = insert(root, key);
        }

        dfs(root, 0);
        System.out.println(level[maxLevel - 1] + " + " + level[maxLevel - 2] + " = " +
                (level[maxLevel - 1] + level[maxLevel - 2]));
    }
}
