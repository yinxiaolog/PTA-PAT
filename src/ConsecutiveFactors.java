import util.Read;

import java.io.IOException;

public class ConsecutiveFactors {
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int max = (int)Math.sqrt(N);

        int start = 0;
        int len = 0;
        for (int i = 2; i <= max; i++) {
            int mul = 1;
            int j;
            for (j = i; j <= max; j++) {
                mul *= j;
                if (N % mul != 0) {
                    break;
                }
            }

            if (len < j - i) {
                len = j - i;
                start = i;
            }
        }

        if (start == 0) {
            System.out.println(1);
            System.out.println(N);
        } else {
            System.out.println(len + 1);
            for (int i = 0; i < len - 1; i++) {
                System.out.print(start + i + "*");
            }
            System.out.println(start + len - 1);
        }
    }
}
