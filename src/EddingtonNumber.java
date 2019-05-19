import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class EddingtonNumber {
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int[] nums = new int[N];
        int[] tmp = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Read.nextInt();
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = nums[nums.length - 1 - i];
        }

        int i = 0;
        for (; i < tmp.length && tmp[i] > i + 1; i++);
        System.out.println(i);
    }
}
