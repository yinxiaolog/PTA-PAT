import util.Read;

import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class SpiralMatrix {
    private static int maxRows(int N) {
        int sqrt = (int)Math.sqrt(N);
        if (sqrt * sqrt == N) {
            return sqrt;
        }

        sqrt++;
        int i;
        for (i = sqrt; N % i != 0; i++);
        return i;
    }

    public static void main(String[] args) throws IOException {
        //System.out.println(maxRows(5));
        Read.init();
        int M = Read.nextInt();
        int[] numbers = new int[M];

        for (int i = 0; i < M; i++) {
            numbers[i] = Read.nextInt();
        }

        Arrays.sort(numbers);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numbers.length; i++) {
            stack.push(numbers[i]);
        }

        int m = maxRows(M);
        int n = M / m;

        int[][] ans = new int[m][n];

        m--;
        n--;

        int i = 0, j = 0;
        while(i <= m && j <= n) {
            if (i == m && j == n) {
                ans[i][j] = stack.pop();
                break;
            }

            if (j == n && i != m) {
                for (int S = i; S <= m; S++) {
                    ans[S][n] = stack.pop();
                    //n--;
                }
                break;
            }
            for (int E = i; E <= n; E++) {
                ans[i][E] = stack.pop();
                //i++;
            }

            for (int S = i + 1; S <= m; S++) {
                ans[S][n] = stack.pop();
                //n--;
            }

            for (int W = n - 1; W >= j; W--) {
                ans[m][W] = stack.pop();
                //m--;
            }

            for (int N = m - 1; N >= i + 1; N--) {
                ans[N][j] = stack.pop();
                //j++;
            }

            i++;
            n--;
            m--;
            j++;
        }

        for(int len = 0; len < ans.length; len++) {
            System.out.println(Arrays.toString(ans[len]).replaceAll("[\\[\\],]", ""));
        }
    }
}