import util.Read;

import java.io.IOException;

public class RootOfAVLTree {
    private static class Node {
        int key;
        int height;
        Node left;
        Node right;

        public Node (int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    private static class AVLTree {
        Node root;

        private Node LLRotation(Node root) {
            Node rootLeft = root.left;
            root.left = rootLeft.right;
            rootLeft.right = root;
            root = rootLeft;

            root.right.height = Math.max(height(root.right.left), height(root.right.right)) + 1;
            root.height = Math.max(height(root.left), height(root.right)) + 1;
            return root;
        }

        private Node RRRotation(Node root) {
            Node rootRight = root.right;
            root.right = rootRight.left;
            rootRight.left = root;
            root = rootRight;

            root.left.height = Math.max(height(root.left.left), height(root.left.right)) + 1;
            root.height = Math.max(height(root.left), height(root.right)) + 1;
            return root;
        }

        private Node LRRotation(Node root) {
            root.left = RRRotation(root.left);
            return LLRotation(root);
        }

        private Node RLRotation(Node root) {
            root.right = LLRotation(root.right);
            return RRRotation(root);
        }

        private int height(Node node) {
            if (node == null) {
                return 0;
            }
            return node.height;
        }

        private Node insert(Node root, int key) {
            if (root == null) {
                root = new Node(key, null, null);
                root.height = Math.max(height(root.left), height(root.right)) + 1;
                return root;
            }

            if (key < root.key) {
                root.left  = insert(root.left, key);
                if (height(root.left) - height(root.right) == 2) {
                    if (key < root.left.key) {
                        root = LLRotation(root);
                    } else {
                        root = LRRotation(root);
                    }
                }
            }

            if (key > root.key) {
                root.right = insert(root.right, key);
                if (height(root.right) - height(root.left) == 2) {
                    if (key < root.right.key) {
                        root = RLRotation(root);
                    } else {
                        root = RRRotation(root);
                    }
                }
            }

            root.height = Math.max(height(root.left), height(root.right)) + 1;
            return root;
        }

        public void insert(int key) {
            root = insert(root, key);
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        AVLTree avlTree = new AVLTree();
        int[] nodes = new int[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = Read.nextInt();
        }

        for (int i = 0; i < N; i++) {
            avlTree.insert(nodes[i]);
        }
        System.out.println(avlTree.root.key);
    }
}
