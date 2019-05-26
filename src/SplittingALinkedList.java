import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplittingALinkedList {
    private static class Node {
        int addr;
        int data;
        int next;

        public Node(int addr, int data, int next) {
            this.addr = addr;
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return addr + " " + data + " " + next;
        }
    }

    private static Node[] linkedList = new Node[100000];

    private static List<Node> split(int head, int K) {
        List<Node> lessThan0 = new ArrayList<>();
        List<Node> from0toK = new ArrayList<>();
        List<Node> greaterThanK = new ArrayList<>();

        for (int i = head;; i = linkedList[i].next) {
            Node node = linkedList[i];
            if (node.data < 0) {
                lessThan0.add(node);
            }

            if (node.data >=0 && node.data <= K) {
                from0toK.add(node);
            }

            if (node.data > K) {
                greaterThanK.add(node);
            }
            if (linkedList[i].next == -1) {
                break;
            }
        }

        lessThan0.addAll(from0toK);
        lessThan0.addAll(greaterThanK);
        return lessThan0;
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        int head = Read.nextInt();
        int N = Read.nextInt();
        int K = Read.nextInt();
        for (int i = 0; i < N; i++) {
            int addr = Read.nextInt();
            int data = Read.nextInt();
            int next = Read.nextInt();
            linkedList[addr] = new Node(addr, data, next);
        }

        List<Node> ans = split(head, K);
        for (int i = 0; i < ans.size() - 1; i++) {
            System.out.printf("%05d %d %05d\n", ans.get(i).addr, ans.get(i).data, ans.get(i + 1).addr);
        }
        System.out.printf("%05d %d %d\n", ans.get(ans.size() - 1).addr, ans.get(ans.size() - 1).data, -1);
    }
}
