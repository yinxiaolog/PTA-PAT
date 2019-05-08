import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class InsertOrMerge {
    private static int[] init;
    private static int[] sorted;
    private static final String insert = "Insertion Sort";
    private static final String merge = "Merge Sort";

    private static String isInsertOrMerge() {
        int i = 0;
        for (; i < sorted.length - 1; i++) {
            if (sorted[i] > sorted[i + 1]) {
                break;
            }
        }

        int j = i + 1;

        for (; j < init.length; j++) {
            if (init[j] != sorted[j]) {
                break;
            }
        }

        if (j == init.length) {
            System.out.println(insert);
            int tmp = sorted[i + 1];
            int k = i;
            for (; k >= 0 && sorted[k] > tmp; k--) {
                sorted[k + 1] = sorted[k];
            }

            sorted[k + 1] = tmp;
            System.out.println(Arrays.toString(sorted).replaceAll("[\\[\\],]", ""));
            return insert;
        } else {
            return merge;
        }
    }

    private static boolean equals(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (sorted[i] != numbers[i]) {
                return false;
            }
        }

        return true;
    }

    private static int[] mergeSort() {
        int[] numbers = init;

        int k = 1;
        boolean flag = false;
        while (!flag) {
            if (equals(numbers)) {
                flag = true;
            }

            k *= 2;

            for (int i = 0; i < numbers.length / k; i++) {
                merge(numbers, i * k, i * k + k - 1);
            }

            merge(numbers,numbers.length / k * k, numbers.length - 1);
        }
        return numbers;
    }

    private static void merge(int[] numbers, int low, int high) {
        int[] tmp = new int[high - low + 1];
        for (int i = low, j = 0; i <= high; i++, j++) {
            tmp[j] = numbers[i];
        }

        Arrays.sort(tmp);
        for (int i = low, j = 0; i <= high; i++, j++) {
            numbers[i] = tmp[j];
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        init = new int[N];
        sorted = new int[N];

        for (int i = 0; i < N; i++) {
            init[i] = Read.nextInt();
        }

        for (int i = 0; i < N; i++) {
            sorted[i] = Read.nextInt();
        }

        if (isInsertOrMerge().equals(merge)) {
            mergeSort();
            System.out.println(merge);
            System.out.println(Arrays.toString(init).replaceAll("[\\[\\],]", ""));
        }
    }
}
