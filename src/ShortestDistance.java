import util.Read;

import java.io.IOException;

public class ShortestDistance {
    private static int[] sum;

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = Read.nextInt();
            sum[i] += sum[i - 1];
        }

        int M = Read.nextInt();
        int[] numbers = new int[M * 2];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Read.nextInt();
        }

        for (int i = 0; i < numbers.length; i += 2) {
            int start = numbers[i];
            int end = numbers[i + 1];

            if (start > end) {
                start = numbers[i + 1];
                end = numbers[i];
            }

            int distance = sum[end - 1] - sum[start - 1];
            System.out.println(Math.min(distance, sum[N] - distance));
        }
    }
}
