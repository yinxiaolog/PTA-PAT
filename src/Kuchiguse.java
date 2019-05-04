import util.Read;

import java.io.IOException;

public class Kuchiguse {
    private static String target;
    private static String[] sentences;

    private static String suffix() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < sentences.length; j++) {
                if (sentences[j].charAt(i) != target.charAt(i)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ans.append(target.charAt(i));
            } else {
                break;
            }
        }

        return ans.reverse().toString();
    }

    public static void main(String[] args) throws IOException{
        Read.init();
        int N = Integer.valueOf(Read.nextLine().trim());
        sentences = new String[N];

        int minLength = Integer.MAX_VALUE;
        StringBuilder sb;
        for (int i = 0; i < N; i++) {
            sb = new StringBuilder(Read.nextLine());
            sentences[i] = sb.reverse().toString();
            if (sb.length() < minLength) {
                minLength = sb.length();
                target = sentences[i];
            }
        }

        String ans = suffix();
        if (ans.length() > 0) {
            System.out.println(ans);
        } else {
            System.out.println("nai");
        }
    }
}
