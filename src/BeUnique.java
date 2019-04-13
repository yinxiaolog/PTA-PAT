import util.Read;

import java.io.IOException;

public class BeUnique {
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int[] numbers = new int[100000];
        int[] count = new int[100001];

        for (int i = 0; i < N; i++) {
            int j = Read.nextInt();
            numbers[i] = j;
            count[numbers[i]]++;
        }

        for (int i = 0; i < N; i++) {
            if (count[numbers[i]] == 1) {
                System.out.println(numbers[i]);
                return;
            }
        }
        System.out.println("None");
    }
}
