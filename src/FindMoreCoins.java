import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMoreCoins {
    private static List<Integer> list = new ArrayList<>();
    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int M = Read.nextInt();
        for (int i = 0; i < N; i++) {
            int coin = Read.nextInt();
            if (coin <= M) {
                list.add(coin);
            }
        }

        Collections.sort(list);
        dp = new boolean[list.size() + 1][M + 1];
        dp[list.size()][0] = true;
        for (int i = list.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= M; j++) {
                //System.out.println(i + " " + j);
                dp[i][j] = dp[i + 1][j] || j >= list.get(i) && dp[i + 1][j - list.get(i)];
            }
        }

        List<Integer> ans = new ArrayList<>();
        if (dp[0][M]) {
            for (int i = 0; i < list.size() && M >= list.get(i);i++) {
                if (dp[i + 1][M - list.get(i)]) {
                    ans.add(list.get(i));
                    M -= list.get(i);
                }
            }
        } else {
            System.out.println("No Solution");
        }

        for (int i = 0; i < ans.size() - 1; i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println(ans.get(ans.size() - 1));
    }
}
