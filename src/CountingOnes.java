import util.Read;

import java.io.IOException;

public class CountingOnes {
    private static int max1s(int N) {
        int left, now, right = 0;
        int a = 1;
        int ans = 0;

        while (N / a > 0) {
            left = N / (a * 10);
            now = N / a % 10;
            right = N % a;

            if (now == 0) {
                ans += left * a;
            } else if (now == 1) {
                ans += left * a + right + 1;
            } else {
                ans += (left + 1) * a;
            }

            a *= 10;
        }

        return ans;
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        System.out.println(max1s(N));
    }
}
