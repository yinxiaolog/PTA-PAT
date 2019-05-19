import util.Read;

import java.io.IOException;

public class CompleteBinaryTree {
    private static class Node {
        int left;
        int right;

        public Node (int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private static Node[] tree;

    private static int max = Integer.MIN_VALUE;
    private static int ans = -1;

    private static void dfs(int root, int index) {
        if (index > max) {
            max = index;
            ans = root;
        }

        if (tree[root].left != -1) {
            dfs(tree[root].left, index * 2 + 1);
        }

        if (tree[root].right != -1) {
            dfs(tree[root].right, index * 2 + 2);
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        tree = new Node[N];

        boolean[] isRoot = new boolean[N];
        for (int i = 0; i < N; i++) {
            String leftStr = Read.next();
            String rightStr = Read.next();
            int left = leftStr.charAt(0) == '-' ? -1 : Integer.valueOf(leftStr);
            int right = rightStr.charAt(0) == '-' ? -1 : Integer.valueOf(rightStr);
            tree[i] = new Node(left, right);
            if (left != -1) {
                isRoot[left] = true;
            }
            if (right != -1) {
                isRoot[right] = true;
            }
        }

        int root = -1;
        for (int i = 0; i < isRoot.length; i++) {
            if (!isRoot[i]) {
                root = i;
                break;
            }
        }

        dfs(root, 0);
        if (max + 1 == N) {
            System.out.println("YES" + " " + ans);
        } else {
            System.out.println("NO" + " " + root);
        }
    }
}
