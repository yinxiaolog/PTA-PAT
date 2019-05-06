import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class PerfectSequence {
    private static long[] numbers;

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        long p = Read.nextInt();
        numbers = new long[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Read.nextInt();
        }

        Arrays.sort(numbers);

        int ans = 0;
        for (int i = 0; i < numbers.length; i++) {
            int j;
            for (j = i + 1; j < numbers.length && p * numbers[i] >= numbers[j]; j++)

            if (ans < j - i) {
                ans = j - i;
            }
        }
        System.out.println(ans);
    }
}
