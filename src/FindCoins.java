import util.Read;

import java.io.IOException;

public class FindCoins {
    private static int[] coins = new int[1001];

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int M = Read.nextInt();

        for (int i = 0; i < N; i++) {
            coins[Read.nextInt()]++;
        }

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > 0) {
                coins[i]--;
                if (i < M && coins[M - i] > 0) {
                    System.out.println(i + " " + (M - i));
                    return;
                }
                coins[i]++;
            }
        }

        System.out.println("No Solution");
    }
}
