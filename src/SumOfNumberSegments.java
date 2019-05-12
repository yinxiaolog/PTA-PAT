import util.Read;

import java.io.IOException;

public class SumOfNumberSegments {
    public static void main(String[] args) throws IOException {
        Read.init();
        int  N = Read.nextInt();
        double sum = 0;
        for (int i = 1; i <= N; i++) {
            double x = Read.nextDouble();
            sum += x * i * (N - i + 1);
        }

        System.out.printf("%.2f", sum);
    }
}
