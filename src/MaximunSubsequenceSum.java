import util.Read;

import java.io.IOException;

public class MaximunSubsequenceSum {
    private static int K;
    private static int[] nums; //= {-1, 1, 2, -9, 2, 1};//{-10, 1, 2, 3, 4, -5, -23, 3, 7, -21};

    public static void main(String[] args) throws IOException {
        Read.init();
        K = Read.nextInt();
        nums = new int[K];
        for (int i = 0; i < K; i++) {
            nums[i] = Read.nextInt();
        }
        System.out.println(maximumSubsequenceSum()[0] + " " + maximumSubsequenceSum()[1] + " " + maximumSubsequenceSum()[2]);
    }

    public static int[] maximumSubsequenceSum() {
        int[] ans = new int[3];
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                flag = true;
            }
        }
        if (!flag) {
            ans[0] = 0;
            ans[1] = nums[0];
            ans[2] = nums[nums.length - 1];
            return ans;
        }

        int start = 0;
        int end = nums.length - 1;
        int index = 0;
        int sum = 0;
        int maxSum = -1;

        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum < 0) {
                sum = 0;
                index = i + 1;
            } else if (sum > maxSum) {
                maxSum = sum;
                start = index;
                end = i;
            }
        }

        if (maxSum < 0) {
            maxSum = 0;
        }
        ans[0] = maxSum;
        ans[1] = nums[start];
        ans[2] = nums[end];
        return ans;
    }
}
/*
class Read {
    private static BufferedReader reader;
    private static StringTokenizer tokenizer;

    static void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}
*/