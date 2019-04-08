import util.Read;

import java.io.IOException;

public class Median {
    public static void main(String[] args) throws IOException {
        Read.init();
        int n, m, tmp, count = 0;
        n = Read.nextInt();
        int[] S1 =  new int[n + 1];
        S1[n] = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            S1[i] = Read.nextInt();
        }
        m = Read.nextInt();
        int midPos = (n + m + 1) / 2;
        int i = 0;
        for (int j = 0; j < m; j++) {
            tmp = Read.nextInt();
            while (S1[i] < tmp) {
                count++;
                if (count == midPos) {
                    System.out.println(S1[i]);
                }
                i++;
            }
            count++;
            if (count == midPos) {
                System.out.println(tmp);
            }
        }
        while (i < n) {
            count++;
            if (count == midPos){
                System.out.println(S1[i]);
            }
            i++;
        }
    }
}
