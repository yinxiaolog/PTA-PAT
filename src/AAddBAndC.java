import util.Read;

import java.io.IOException;
import java.math.BigInteger;

public class AAddBAndC {
    public static void main(String[] args) throws IOException {
        Read.init();

        int T = Read.nextInt();
        for (int i = 1; i <= T; i++) {
            String addendA = Read.next();
            String addendB = Read.next();
            String addendC = Read.next();
            BigInteger A = new BigInteger(addendA);
            BigInteger B = new BigInteger(addendB);
            BigInteger C = new BigInteger(addendC);
            if (A.add(B).compareTo(C) > 0) {
                System.out.println("Case #" + i + ": " + true);
            } else {
                System.out.println("Case #" + i + ": " + false);
            }
        }
    }
}
