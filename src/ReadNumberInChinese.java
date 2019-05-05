import util.Read;

import java.io.IOException;

public class ReadNumberInChinese {
    private static String[] numbers = {"ling", "yi", "er", "san", "si", "wu", "liu", "qi", "ba", "jiu"};
    private static String[] place = {"Shi","Bai", "Qian", "Wan", "Yi"};
    private static String[] read = new String[9];

    public static void main(String[] args) throws IOException {
        Read.init();

        String number = Read.next();
        if (number.length() == 1 && number.charAt(0) == '0') {
            System.out.println(numbers[0]);
            return;
        }

        char sign = '+';
        if (number.charAt(0) == '-') {
            sign = '-';
            number = number.substring(1);
        }

        if (number.length() < 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9 - number.length(); i++) {
                sb.append('0');
            }
            sb.append(number);
            number = sb.toString();
        }

        //System.out.println(number);

        read[0] = numbers[number.charAt(0) - '0'];
        read[1] = numbers[number.charAt(1) - '0'] + " " + place[2];
        read[2] = numbers[number.charAt(2) - '0'] + " " + place[1];
        read[3] = numbers[number.charAt(3) - '0'] + " " + place[0];
        read[4] = numbers[number.charAt(4) - '0'];
        read[5] = numbers[number.charAt(5) - '0'] + " " + place[2];
        read[6] = numbers[number.charAt(6) - '0'] + " " + place[1];
        read[7] = numbers[number.charAt(7) - '0'] + " " + place[0];
        read[8] = numbers[number.charAt(8) - '0'];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < read.length; i++) {
            if (number.charAt(i) - '0' == 0) {
                sb.append(numbers[0]).append(" ");
            } else {
                sb.append(read[i]).append(" ");
            }

            if (i == 0) {
                sb.append(place[4]).append(" ");
            }

            if (i == 4) {
                sb.append(place[3]).append(" ");
            }
        }

        String ans = sb.toString();

        ans = ans.replace("ling Yi", "");
        //System.out.println(ans);
        ans = ans.replaceAll("(ling )+", "ling ");
        ans = ans.replaceAll("ling Wan", "Wan");
        ans = ans.trim();
        ans = ans.replaceAll("[\\s]", " ");
        //System.out.println(ans);
        String[] strings = ans.split(" ");
        //System.out.println(ans);

        if (strings[0].equals(place[3])) {
            ans = ans.replaceAll("Wan ling ", "");
            ans = ans.replaceAll("Wan", "");
        }
        if (strings[0].equals(strings[strings.length - 1]) && strings[0].equals(numbers[0])) {
            ans = ans.substring(4, ans.length() - 4);
            ans = ans.trim();
            if (sign == '-') {
                ans = "Fu " + ans;
            }
            System.out.println(ans);
            return;
        }

        if (strings[0].equals(numbers[0])) {
            ans = ans.substring(4);
            ans = ans.trim();
            if (sign == '-') {
                ans = "Fu " + ans;
            }
            System.out.println(ans);
            return;
        }

        if (strings[strings.length - 1].equals(numbers[0])) {
            ans = ans.substring(0, ans.length() - 4);
            ans = ans.trim();
            if (sign == '-') {
                ans = "Fu " + ans;
            }
            System.out.println(ans);
            return;
        }

        ans = ans.trim();
        if (sign == '-') {
            ans = "Fu " + ans;
        }
        System.out.println(ans);
        return;
    }
}
