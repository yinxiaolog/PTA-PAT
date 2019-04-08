import util.Read;

import java.io.IOException;

public class HelloWorldForU {
    private static int[] calN1AndN3(int N) {
        int[] ans = new int[2];
        for (int i = 3; i <= N; i++) {
            if ((N + 2 - i) % 2 == 0 && (N + 2 - i) / 2 <= i) {
                ans[0] = (N + 2 - i) / 2;
                ans[1] = i;
                return ans;
            }
        }
        return null;
    }

    private static String spaceNum(int N) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < N; i++) {
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        String s = Read.next();
        int[] ans;
        ans = calN1AndN3(s.length());
        int n1 = ans[0];
        int n2 = ans[1];

        for (int i = 0; i < n1 - 1;i++) {
            System.out.println(s.charAt(i) + spaceNum(n2 - 2) + s.charAt(s.length() - 1 - i));
        }
        for (int i = 0; i < n2; i++) {
            System.out.print(s.charAt(n1 - 1 + i));
        }
    }
}
