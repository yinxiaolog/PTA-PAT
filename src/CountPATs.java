import util.Read;

import java.io.IOException;

public class CountPATs {
    public static void main(String[] args) throws IOException {
        Read.init();
        String str = Read.next();

        int max = 1000000007;
        long countP = 0;
        long countPA = 0;
        long countPAT = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'P') {
                countP++;
                countP %= max;
            }

            if (str.charAt(i) == 'A') {
                countPA += countP;
                countPA %= max;
            }

            if (str.charAt(i) == 'T') {
                countPAT += countPA;
                countPAT %= max;
            }
        }

        System.out.println(countPAT);
    }
}
