import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingInMars {
    private static int[] sum;
    private static int M;
    private static int min;

    private static List<String> windowSliding() {
        List<String> ans = new ArrayList<>();

        int left = 0;
        min = sum[sum.length - 1];
        for (int right = 1; right < sum.length; right++) {
            while (sum[right] - sum[left] > M) {
                left++;
            }

            if (sum[right] - sum[left] == M) {
                ans.add(left + 1 + "-" + right);
            }

            if (left > 0 && sum[right] - sum[left - 1] > M) {
                min = Math.min(min, sum[right] - sum[left - 1]);
            }
        }
        //System.out.println(min);
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        sum = new int[N + 1];
        M = Read.nextInt();

        for (int i = 1; i <= N; i++) {
            sum[i] = Read.nextInt();
            sum[i] += sum[i - 1];
        }

        List<String> ans = windowSliding();
        if (ans.size() > 0) {
            for (String s : ans) {
                System.out.println(s);
            }
        } else {
            M = min;
            ans = windowSliding();
            for (String s : ans) {
                System.out.println(s);
            }
        }
    }
}
