import util.Read;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarsNumbers {
    private static String[] digit = {"tret", "jan", "feb", "mar", "apr", "may", "jun",
            "jly", "aug", "sep", "oct", "nov", "dec"};
    private static String[] higherDigit = {"***", "tam", "hel", "maa", "huh", "tou", "kes",
            "hei", "elo", "syy", "lok", "mer", "jou"};

    private static boolean isEarthOrMars(String str) {
        String pattern = "[0-9]+";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }

    private static String earth2Mars(String str) {
        int x = Integer.valueOf(str);
        if (x == 0) {
            return digit[0];
        }
        StringBuilder ans = new StringBuilder();
        if (x / 13 > 0) {
            ans.append(higherDigit[x / 13]).append(' ');
        }

        if (x % 13 == 0) {
            return ans.toString().trim();
        }
        ans.append(digit[x % 13]);
        return ans.toString();
    }

    private static int findDigit(String str) {
        for (int i = 0; i < digit.length; i++) {
            if (str.equals(digit[i])) {
                return i;
            }
        }

        return -1;
    }

    private static int findHigherDigit(String str) {
        for (int i = 0; i < higherDigit.length; i++) {
            if (str.equals(higherDigit[i])) {
                return i;
            }
        }

        return -1;
    }

    private static int mars2Earth(String str) {
        String[] strings = str.split(" ");
        int ans = 0;
        if (strings.length > 1) {
            ans += findHigherDigit(strings[0]) * 13;
            ans += findDigit(strings[1]);
        }

        if (strings.length == 1) {
            if (findHigherDigit(str) == -1) {
                ans += findDigit(str);
            } else {
                ans += findHigherDigit(str) * 13;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Integer.valueOf(Read.nextLine());
        for (int i = 0; i < N; i++) {
            String number = Read.nextLine();
            if (isEarthOrMars(number)) {
                System.out.println(earth2Mars(number));
            } else {
                System.out.println(mars2Earth(number));
            }
        }
    }
}
