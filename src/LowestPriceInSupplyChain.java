import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LowestPriceInSupplyChain {
    private static double P;
    private static double rate;
    private static List<Integer>[] tree;

    private static int count = 0;
    private static double minPrice = Double.MAX_VALUE;

    private static void dfs(int root, int depth) {
        if (tree[root].size() == 0) {
            double price = P * Math.pow(rate, depth);
            if (price < minPrice) {
                count = 1;
                minPrice = price;
            } else if (price == minPrice) {
                count++;
            }
        }

        for (Integer i : tree[root]) {
            dfs(i, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        tree = new List[N];
        P = Read.nextDouble();
        rate = Read.nextDouble();
        rate = 1 + rate / 100;

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int K = Read.nextInt();
            for (int j = 0; j < K; j++) {
                tree[i].add(Read.nextInt());
            }
        }

        dfs(0, 0);
        System.out.printf("%.4f %d", minPrice, count);
    }
}
