import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int[] raw = new int[N];
        int[] sorted = new int[N];
        for (int i = 0; i < N; i++) {
            raw[i] = sorted[i] = Read.nextInt();
        }

        Arrays.sort(sorted);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int max = -1;
        for (int i = 0; i < raw.length; i++) {
            if (raw[i] == sorted[i] && raw[i] > max) {
                count++;
                sb.append(raw[i]).append(' ');
            }

            if (raw[i] > max) {
                max = raw[i];
            }
        }

        if (count == 0) {
            System.out.println(0);
            System.out.println();
        }
        System.out.println(count);
        System.out.println(sb.toString().trim());
    }
}
