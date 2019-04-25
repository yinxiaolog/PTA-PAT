import util.Read;

import java.io.IOException;

public class AAndBInHogwarts {
    private static final int Galleon = 10000000;
    private static final int Sickle = 17;
    private static final int Knut = 29;

    private static long[] add(long[] numA, long[] numB) {
        long[] ans = new long[numA.length];

        ans[1] = (numA[2] + numB[2]) / Knut;
        ans[2] = (numA[2] + numB[2]) % Knut;

        ans[0] = (ans[1] + numA[1] + numB[1]) / Sickle;
        ans[1] = (ans[1] + numA[1] + numB[1]) % Sickle;

        ans[0] += numA[0] + numB[0];

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        String addendA = Read.next();
        String addendB = Read.next();
        String[] addendArr1 = addendA.split("\\.");
        String[] addendArr2 = addendB.split("\\.");
        long[] numA = new long[addendArr1.length];
        long[] numB = new long[addendArr2.length];
        for (int i = 0; i < numA.length; i++) {
            numA[i] = Long.parseLong(addendArr1[i]);
        }

        for (int i = 0; i < numB.length; i++) {
            numB[i] = Long.parseLong(addendArr2[i]);
        }

        long[] ans = add(numA, numB);
        System.out.println(ans[0] + "." + ans[1] + "." + ans[2]);
    }
}
