import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class ChainTheRopes {
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Read.nextInt();
        }

        Arrays.sort(nums);
        double ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans = (ans + nums[i]) / 2;
        }

        System.out.println((int)Math.floor(ans));
    }
}
