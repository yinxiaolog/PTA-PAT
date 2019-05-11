import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TheLargestGeneration {
    private static List<Integer>[] tree;

    private static int[] marked;

    private static void dfs(int index, int level) {
        marked[level]++;
        for (Integer i : tree[index]) {
            dfs(i, level + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int M = Read.nextInt();

        tree = new List[N + 1];
        marked = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int id = Read.nextInt();
            int K = Read.nextInt();
            for (int j = 0; j < K; j++) {
                tree[id].add(Read.nextInt());
            }
        }

        dfs(1, 1);
        int max = -1, maxLevel = -1;
        for (int i = 0; i < marked.length; i++) {
            if (marked[i] > max) {
                max = marked[i];
                maxLevel = i;
            }
        }
        System.out.println(max + " " + maxLevel);
    }
}
