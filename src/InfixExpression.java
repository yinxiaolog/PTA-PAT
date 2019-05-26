import util.Read;

import java.io.IOException;

public class InfixExpression {
    private static class Node {
        String key;
        int left;
        int right;

        public Node(String key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    private static Node[] tree;

    private static String dfs(int root) {
        if(tree[root].left == -1 && tree[root].right == -1) {
            return tree[root].key;
        }

        if(tree[root].left == -1 && tree[root].right != -1) {
            return '(' + tree[root].key + dfs(tree[root].right) + ')';
        }

        if (tree[root].left != -1 && tree[root].right != -1) {
            return '(' + dfs(tree[root].left) + tree[root].key + dfs(tree[root].right) + ')';
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int root = -1;
        tree = new Node[N + 1];
        boolean[] isRoot = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            String key = Read.next();
            int left = Read.nextInt();
            int right = Read.nextInt();
            tree[i] = new Node(key, left, right);
            if (left != -1) {
                isRoot[left] = true;
            }

            if (right != -1) {
                isRoot[right] = true;
            }
        }

        for (int i = 1; i < isRoot.length; i++) {
            if (!isRoot[i]) {
                root = i;
            }
        }

        String ans = dfs(root);

        if (ans.charAt(0) == '(') {
            ans = ans.substring(1, ans.length() - 1);
        }
        System.out.println(ans);
    }
}
