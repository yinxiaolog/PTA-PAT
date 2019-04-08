import util.Read;

import java.io.IOException;

public class MainPolynomials {
    public static void main(String[] args) throws IOException {
        Read.init();
        double[] A = new double[1001];
        double[] B = new double[1001];
        int K = Read.nextInt();
        for (int i = 0; i < K; i++) {
            int index = Read.nextInt();
            A[index] = Read.nextDouble();
        }

        K = Read.nextInt();
        for (int i = 0; i < K; i++) {
            int index = Read.nextInt();
            B[index] = Read.nextDouble();
        }

        double[] ans = new double[1001];
        int ansK = 0;
        for (int i = 0; i < ans.length; i++) {
            ans[i] = A[i] + B[i];
            if (ans[i] != 0) {
                ansK++;
            }
        }

        System.out.print(ansK);

        for (int i = ans.length - 1; i >= 0; i--) {
            if (ans[i] != 0) {
                System.out.format(" %d %.1f", i, ans[i]);
            }
        }
    }
}
