import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteColorStripe {
    private static int[] colors;
    private static List<Integer> stripe = new ArrayList<>();

    private static int dp() {
        int[] dpArr = new int[stripe.size()];
        int ans = 0;
        for (int i = 0; i < stripe.size(); i++) {
            dpArr[i] = 1;
            for (int j = 0; j < i; j++) {
                if (stripe.get(j) <= stripe.get(i)) {
                    dpArr[i] = Math.max(dpArr[i], dpArr[j] + 1);
                }
            }

            ans = Math.max(ans, dpArr[i]);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        colors = new int[N + 1];
        int M = Read.nextInt();
        for (int i = 1; i <= M; i++) {
            colors[Read.nextInt()] = i;
        }

        int L = Read.nextInt();
        for (int i = 0; i < L; i++) {
            int tmp = Read.nextInt();
            if (colors[tmp] > 0) {
                stripe.add(colors[tmp]);
            }
        }

        System.out.println(dp());
    }
}
