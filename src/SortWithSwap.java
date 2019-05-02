import util.Read;

import java.io.IOException;

public class SortWithSwap {
    private static int[] numbers;
    private static boolean[] marked;

    private static int findRing(int index) {
        int ring = 0;
        boolean flag = false;
        for(; !marked[index]; index = numbers[index]) {
            if (index == 0) {
                flag = true;
            }
            ring++;
            marked[index] = true;
        }

        return flag ? ring - 1 : ring <= 1 ? 0 : ring + 1;
    }

    private static int sort() {
        int ans = 0;
        for (int i = 0; i < numbers.length; i++) {
            ans += findRing(i);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        numbers = new int[N];
        marked = new boolean[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Read.nextInt();
        }

        System.out.println(sort());
    }
}
