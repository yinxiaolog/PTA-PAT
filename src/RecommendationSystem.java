import util.Read;

import java.io.IOException;
import java.util.TreeSet;

public class RecommendationSystem {
    private static class Node implements Comparable<Node> {
        int id;
        int count;

        public Node(int id, int count) {
            this.id = id;
            this.count = count;
        }

        @Override
        public int compareTo(Node another) {
            if (count < another.count) {
                return 1;
            }

            if (count > another.count) {
                return -1;
            }

            if (id < another.id) {
                return -1;
            }

            if (id > another.id) {
                return 1;
            }

            return 0;
        }

        @Override
        public String toString() {
            return id + " " + count;
        }
    }

    private static TreeSet<Node> set = new TreeSet<>();

    private static String output(int K) {
        StringBuilder ans = new StringBuilder();

        for (Node node : set) {
            if(K <= 0) {
                break;
            }
            ans.append(' ').append(node.id);
            K--;
        }

        return ans.toString();
    }

    private static Node find(int id) {
        for (Node node : set) {
            if (id == node.id) {
                return node;
            }
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int K = Read.nextInt();

        for (int i = 0; i < N; i++) {
            int rec = Read.nextInt();

            if (i > 0) {
                System.out.println(rec + ":" + output(K));
            }

            Node node = find(rec);
            if (node == null) {
                set.add(new Node(rec, 1));
            } else {
                int id = node.id;
                int count = node.count;
                set.remove(node);
                set.add(new Node(id, count + 1));
            }
        }
    }
}
