import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvertABinaryTree {
    private static class Node implements Comparable<Node>{
        int key;
        int left;
        int right;
        int index;
        int level;

        public Node(int key, int left, int right) {
            this.key = key;
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
                return 1;
            }

            if (index > another.index) {
                return -1;
            }

            return 0;
        }

        @Override
        public String toString() {
            return String.valueOf(key);
        }
    }

    private static Node[] tree;
    private static boolean[] haveRoot;
    private static List<Node> ans = new ArrayList<>();

    private static void dfs(int root, int index, int level) {
        if (tree[root].right != -1) {
            dfs(tree[root].right, index * 2 + 2, level + 1);
        }

        Node node = tree[root];
        node.index = index;
        node.level = level;
        ans.add(node);

        if (tree[root].left != -1) {
            dfs(tree[root].left, index * 2 + 1, level + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        tree = new Node[N];
        haveRoot = new boolean[N];

        for (int i = 0; i < N; i++) {
            String left = Read.next();
            String right = Read.next();
            int l = left.charAt(0) == '-' ? -1 : Integer.valueOf(left);
            int r = right.charAt(0) == '-' ? -1 : Integer.valueOf(right);

            tree[i] = new Node(i, l, r);
            if (l >= 0 && !haveRoot[l]) {
                haveRoot[l] = true;
            }
            if (r >= 0 && !haveRoot[r]) {
                haveRoot[r] = true;
            }
        }

        int root = -1;
        for (int i = 0; i < haveRoot.length; i++) {
            if (!haveRoot[i]) {
                root = i;
            }
        }

        dfs(root, 0, 0);
        String inOrder = ans.toString().replaceAll("[\\[\\],]", "");
        Collections.sort(ans);
        System.out.println(ans.toString().replaceAll("[\\[\\],]", ""));
        System.out.println(inOrder);
    }
}
