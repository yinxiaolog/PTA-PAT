import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReversingLinkedList {
    private static class Node {
        int address;
        int data;
        int next;

        public Node(int address, int data, int next) {
            this.address = address;
            this.data = data;
            this.next = next;
        }
    }

    private static Node[] list = new Node[100001];

    public static void main(String[] args) throws IOException {
        Read.init();
        int head = Read.nextInt();
        int N = Read.nextInt();
        int K = Read.nextInt();
        for (int i = 0; i < N; i++) {
            int address = Read.nextInt();
            int data = Read.nextInt();
            int next = Read.nextInt();
            list[address] = new Node(address, data, next);
        }

        List<Node> nodeList = new ArrayList<>();
        for (int i = head; i != -1 && list[i] != null; i = list[i].next) {
            //System.out.println(i);
            nodeList.add(list[i]);
        }

        nodeList.get(nodeList.size() - 1).next = -1;

        head = nodeList.get(K - 1).next;
        for (int i = K -1; i >= 0; i--) {
            Node node = nodeList.get(i);
            if (i != 0) {
                System.out.printf("%05d %d %05d\n", node.address, node.data, nodeList.get(i -1).address);
            } else {
                if (head != -1) {
                    System.out.printf("%05d %d %05d\n", node.address, node.data, head);
                } else {
                    System.out.printf("%05d %d %d\n", node.address, node.data, head);
                }
            }
        }

        for (int i = K; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            if (i != nodeList.size() - 1) {
                System.out.printf("%05d %d %05d\n", node.address, node.data, node.next);
            } else {
                System.out.printf("%05d %d %d\n", node.address, node.data, -1);
            }
        }
    }
}
