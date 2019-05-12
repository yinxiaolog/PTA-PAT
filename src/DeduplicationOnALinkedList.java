import util.Read;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeduplicationOnALinkedList {
    private static class Node {
        int address;
        int key;
        int next;

        public Node(int address, int key, int next) {
            this.address = address;
            this.key = key;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("%05d %d %05d", address, key, next);
        }
    }

    private static boolean[] marked = new boolean[100000];
    private static Node[] delDeduplication = new Node[100000];
    private static Node[] deduplication = new Node[100000];
    private static int head1 = -1;
    private static int head2 = -1;

    private static void solve(Map<Integer, Node> map, int head) {
        int headDel = -1;
        int headDed = -1;

        while (map.size() > 0) {
            Node node = map.get(head);
            if (marked[Math.abs(node.key)]) {
                if (headDed < 0) {
                    headDed = node.address;
                    head2 = node.address;
                    deduplication[headDed] = node;
                } else {
                    deduplication[headDed].next = node.address;
                    headDed = node.address;
                    deduplication[headDed] = node;
                }
            } else {
                if (headDel < 0) {
                    headDel = node.address;
                    head1 = node.address;
                    delDeduplication[headDel] = node;
                } else {
                    delDeduplication[headDel].next = node.address;
                    headDel = node.address;
                    delDeduplication[headDel] = node;
                }
            }

            marked[Math.abs(node.key)] = true;
            if (map.get(node.next) == null) {
                break;
            }
            map.remove(head);
            head = node.next;
        }
    }

    private static void printList(int head, Node[] list) {
        if (head < 0) {
            return;
        }
        for (int i = head; list[head].next != -1 && list[list[head].next] != null ;head = list[head].next) {
            System.out.println(list[head]);
        }
        System.out.printf("%05d %d %d\n", list[head].address, list[head].key, -1);
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int head = Read.nextInt();
        int N = Read.nextInt();

        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            Node node = new Node(Read.nextInt(), Read.nextInt(), Read.nextInt());
            nodeMap.put(node.address, node);
        }

        solve(nodeMap, head);
        printList(head1, delDeduplication);
        printList(head2, deduplication);
    }
}
