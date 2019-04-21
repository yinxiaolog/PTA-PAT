import util.Read;

import java.io.IOException;

public class StringSubtraction {
    public static void main(String[] args) throws IOException {
        Read.init();
        String S1 = Read.nextLine();
        String S2 = Read.nextLine();
        boolean[] flag = new boolean[128];

        for (int i = 0; i < S2.length(); i++) {
            flag[S2.charAt(i)] = true;
        }

        for (int i = 0; i < S1.length(); i++) {
            char c = S1.charAt(i);
            if (!flag[c]) {
                System.out.print(c);
            }
        }
    }
}
