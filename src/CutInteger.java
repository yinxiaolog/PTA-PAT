import util.Read;

import java.io.IOException;

public class CutInteger {
    private static String solve(String str) {
        String s1 = str.substring(0, str.length() / 2);
        String s2 = str.substring(str.length() / 2, str.length());

        if (Integer.valueOf(s1).equals(0) || Integer.valueOf(s2).equals(0)) {
            return "No";
        }

        int x = Integer.valueOf(str);
        int i = Integer.valueOf(s1);
        int j = Integer.valueOf(s2);


        if (x % (i * j) == 0) {
            return "Yes";
        } else {
            return "No";
        }
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        for (int i = 0; i < N; i++) {
            String str = Read.next();
            System.out.println(solve(str));
        }
    }
}
