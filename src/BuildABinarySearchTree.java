import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class BuildABinarySearchTree {
    private static class Node implements Comparable<Node> {
        int key;
        int left;
        int right;
        int index;
        int level;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node another) {
            if (level < another.level) {
                return -1;
            }

            if (level > another.level) {
                return 1;
            }

            if (index < another.index) {
                return -1;
            }

            if (index > another.index) {
                return 1;
            }

            return 0;
        }
    }

    private static Node[] tree;
    private static int[] keys;
    private static int count;

    private static void dfs(int root, int index, int level) {
        if (tree[root].left == -1 && tree[root].right == -1) {
            tree[root].key = keys[count++];
            tree[root].index = index;
            tree[root].level = level;
            return;
        }

        if (tree[root].left != -1) {
            dfs(tree[root].left, index * 2 + 1, level + 1);
        }
        tree[root].key = keys[count++];
        tree[root].index = index;
        tree[root].level = level;
        if (tree[root].right != -1) {
            dfs(tree[root].right, index * 2 + 2, level + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        tree = new Node[N];
        keys = new int[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new Node(Read.nextInt(), Read.nextInt());
        }

        for (int i = 0; i < N; i++) {
            keys[i] = Read.nextInt();
        }

        Arrays.sort(keys);
        dfs(0, 0 ,0);
        Arrays.sort(tree);
        for (int i = 0; i < tree.length - 1; i++) {
            System.out.print(tree[i].key + " ");
        }
        System.out.println(tree[tree.length - 1].key);
    }
}
