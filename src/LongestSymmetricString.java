import util.Read;

import java.io.IOException;

public class LongestSymmetricString {
    private static int manacher(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < s.length(); i++) {
            stringBuilder.append('#').append(s.charAt(i));
        }
        stringBuilder.append('#').append(']');

        int[] length = new int[stringBuilder.length()];

        int midIndex = 1;
        int rightIndex = 1;
        int maxLenIndex = -1;
        int maxLen = 0;
        for (int i = 2; i < stringBuilder.length() - 2; i++) {
            if (i <= rightIndex) {
                length[i] = Math.min(length[2 * midIndex - i], rightIndex - i);
            }

            while (stringBuilder.charAt(i - length[i] - 1) == stringBuilder.charAt(i + length[i] + 1)) {
                length[i]++;
            }

            if (length[i] + i > rightIndex) {
                midIndex = i;
                rightIndex = length[i] + i;
            }
            if (length[i] > maxLen) {
                maxLen = length[i];
                maxLenIndex = i;
            }
        }

        String tmp = stringBuilder.substring(maxLenIndex - length[maxLenIndex], maxLenIndex + length[maxLenIndex]);
        tmp.replace("#","");
        int ans =  tmp.replace("#","").length();
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        String s = Read.nextLine();
        System.out.println(manacher(s));
    }
}
