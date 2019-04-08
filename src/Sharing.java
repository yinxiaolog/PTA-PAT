import util.Read;

import java.io.IOException;

class Node {
    int address;
    String data;
    int next;
    boolean flag = false;
}
public class Sharing {
    private static Node[] nodes = new Node[100000];

    private static void markedNodes(int head) {
        while (head != -1) {
            nodes[head].flag = true;
            head = nodes[head].next;
        }
    }

    private static int getCommon(int head) {
        while (head != -1) {
            if (nodes[head].flag == true) {
                return head;
            }
            nodes[head].flag = true;
            head = nodes[head].next;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int head1 = Read.nextInt();
        int head2 = Read.nextInt();
        int N = Read.nextInt();

        for (int i = 0; i < N; i++) {
            Node node = new Node();
            node.address = Read.nextInt();
            node.data = Read.next();
            node.next = Read.nextInt();
            nodes[node.address] = node;
        }
        markedNodes(head1);
        int ans = getCommon(head2);
        if (ans != -1) {
            System.out.printf("%05d\n", ans);
        } else {
            System.out.println(ans);
        }
    }
}
