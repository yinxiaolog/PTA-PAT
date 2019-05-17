import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class SocialClusters {
    private static int[] par;
    private static int[] rank;
    private static int[] root;

    private static void init(int n) {
        for (int i = 0; i <= n; i++) {
            par[i] = i;
            rank[i] = 0;
        }
    }

    private static int find(int x) {
        if (par[x] == x) {
            return x;
        } else {
            return par[x] = find(par[x]);
        }
    }

    private static void unite(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return;
        }

        if (rank[x] < rank[y]) {
            par[x] = y;
        } else {
            par[y] = x;
            if (rank[x] == rank[y]) {
                rank[x]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int[] hobby = new int[1001];

        par = new int[N+1];
        rank = new int[N+1];
        init(N);
        root = new int[N+1];

        for (int i = 1; i <= N; i++) {
            String str = Read.next();
            int K = Integer.valueOf(str.substring(0,1));
            for (int j = 0; j < K; j++) {
                int h = Read.nextInt();
                if (hobby[h] == 0) {
                    hobby[h] = i;
                }

                unite(i, find(hobby[h]));
            }
        }

        for (int i = 1; i <= N; i++) {
            root[find(i)]++;
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if(root[i] != 0) {
                count++;
            }
        }
        System.out.println(count);
        Arrays.sort(root);
        for (int i = root.length - 1; i > root.length - count; i--) {
            System.out.print(root[i] + " ");
        }
        System.out.println(root[root.length - count]);
    }
}
