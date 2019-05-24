import util.Read;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NQueensPuzzle {
    private static boolean solve(int[] queens) {
        boolean flag = true;
        for (int i = 0; i < queens.length; ++i) {
            for (int j = 1; j < i; ++j) {
                if (Math.abs(i - j) == Math.abs(queens[i] - queens[j])) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }
    public static void main(String[] args) throws IOException {
        Read.init();

        int K = Read.nextInt();
        for (int i = 0; i < K; i++) {
            int N = Read.nextInt();
            Set<Integer> set = new HashSet<>();
            int[] queens = new int[N];
            for (int j = 0; j < N; j++) {
                int queen = Read.nextInt();
                set.add(queen);
                queens[j] = queen;
            }
            if (set.size() != N) {
                System.out.println("NO");
                break;
            }

            if (solve(queens)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
