import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class IntegerSetPartition {
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        long[] numbers = new long[N];
        Arrays.sort(numbers);
        long sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Read.nextLong();
            sum += numbers[i];
        }

        Arrays.sort(numbers);
        long sumHalf = 0;
        int halfIndex = N / 2;
        for (int i = 0; i < halfIndex; i++) {
            sumHalf += numbers[i];
        }

        System.out.println(N % 2 + " " + (sum - 2*sumHalf));
    }
}
