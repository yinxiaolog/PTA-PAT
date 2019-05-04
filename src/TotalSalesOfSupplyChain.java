import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TotalSalesOfSupplyChain {
    private static class Node {
        int data;
        List<Integer> child = new ArrayList<>();
    }

    private static Node[] nodes;
    private static double price;
    private static double rate;
    private static double ans;

    private static void dfs(int v, int depth) {
        //depth++;
        if (nodes[v].child.size() == 0) {
            ans += nodes[v].data * price * Math.pow(rate, depth);
            return;
        }

        for (int i = 0; i < nodes[v].child.size(); i++) {
            dfs(nodes[v].child.get(i), depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        nodes = new Node[N];
        price = Read.nextDouble();
        rate = 1 + Read.nextDouble()/100;

        for (int i = 0; i < N; i++) {
            int K = Read.nextInt();
            if (K == 0) {
                Node node = new Node();
                node.data = Read.nextInt();
                nodes[i] = node;
            } else {
                for (int j = 0; j < K; j++) {
                    int child = Read.nextInt();
                    if (nodes[i] == null) {
                        Node node = new Node();
                        node.child.add(child);
                        nodes[i] = node;
                    } else {
                        nodes[i].child.add(child);
                    }
                }
            }
        }

        dfs(0,0);
        System.out.printf("%.1f", ans);
    }
}
