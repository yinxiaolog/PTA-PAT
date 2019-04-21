import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinkedListSorting {
    private static class Node implements Comparable<Node>{
        int address;
        int key;
        int next;

        public int compareTo(Node another) {
            if (key < another.key) {
                return -1;
            } else if(key > another.key) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private static Node[] nodes = new Node[100000];

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int head = Read.nextInt();
        for (int i = 0; i < N; i++) {
            Node node = new Node();
            node.address = Read.nextInt();
            node.key = Read.nextInt();
            node.next = Read.nextInt();
            nodes[node.address] = node;
        }

        List<Node> nodeList = new ArrayList<>();
        while (head != -1){
            Node node = nodes[head];
            nodeList.add(node);
            head = node.next;
        }
        Collections.sort(nodeList);

        if (nodeList.size() == 0) {
            System.out.println(0 + " " + -1);
            return;
        }

        if (nodeList.size() == 1) {
            Node node = nodeList.get(0);
            System.out.printf("%d %05d\n", nodeList.size(), node.address);
            System.out.printf("%05d %d %d\n", node.address, node.key, -1);
            return;
        }
        System.out.printf("%d %05d\n", nodeList.size(), nodeList.get(0).address);
        for (int i = 0; i < nodeList.size() - 1; i++) {
            Node node = nodeList.get(i);
            System.out.printf("%05d %d %05d\n", node.address, node.key, nodeList.get(i+1).address);
        }
        Node node = nodeList.get(nodeList.size() - 1);
        System.out.printf("%05d %d %d\n", node.address, node.key, -1);
    }
}
