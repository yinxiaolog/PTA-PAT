import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegerFactorization {
    private static List<Integer> pow = new ArrayList<>();
    private static int N;
    private static int K;
    private static int P;

    private static void init(int n) {
        int i = 0;
        int tmp = (int)Math.pow(i, P);
        for (; tmp <= N; i++) {
            tmp = (int)Math.pow(i, P);
            pow.add(tmp);
        }
    }

    private static int maxSum = -1;
    private static List<Integer> ans = new ArrayList<>();
    private static int[] tmpAns;

    private static void dfs(int index, int tmpSum, int tmpK, int sum) {
        if (tmpK == K) {
            if (tmpSum == N && sum > maxSum) {
                ans.clear();
                for (int i = 0; i < tmpAns.length; i++) {
                    ans.add(tmpAns[i]);
                }
                maxSum = sum;
            }

            return;
        }

        while (index >= 1) {
            if (tmpSum + pow.get(index) <= N) {
                tmpAns[tmpK] = index;
                dfs(index, tmpSum + pow.get(index), tmpK + 1, sum + index);
            }

            if (index == 1) {
                return;
            }

            index--;
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        N = Read.nextInt();
        K = Read.nextInt();
        P = Read.nextInt();
        init(N);
        tmpAns = new int[K];
        dfs(pow.size() - 1, 0, 0, 0);
        if (maxSum == -1) {
            System.out.println("Impossible");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(N).append(" = ");
        for (int i = 0; i < ans.size() - 1; i++) {
            sb.append(ans.get(i)).append('^').append(P).append(" + ");
        }

        sb.append(ans.get(ans.size() - 1)).append('^').append(P);
        System.out.println(sb.toString());
    }
}
