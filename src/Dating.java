import util.Read;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dating {
    private static Map<Character, String> weeks = new HashMap<>();
    private static char[] hours = new char[24];

    private static void init() {
        weeks.put('A', "MON");
        weeks.put('B', "TUE");
        weeks.put('C', "WED");
        weeks.put('D', "THU");
        weeks.put('E', "FRI");
        weeks.put('F', "SAT");
        weeks.put('G', "SUN");

        char c = '0';
        for (int i = 0; i <=9; i++) {
            hours[i] = c;
            c++;
        }

        c = 'A';
        for (int i = 10; i < hours.length; i++) {
            hours[i] = c;
            c++;
        }
    }

    private static int findHour(char c) {
        for (int i = 0; i < hours.length; i++) {
            if (c == hours[i]) {
                return i;
            }
        }
        return -1;
    }

    private static String findCommonSub(String s1, String s2) {
        StringBuilder ans = new StringBuilder();
        int i = 0;
        for (; i < s1.length() && i < s2.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 == c2 && c1 >= 'A' && c1 <= 'G') {
                ans.append(s1.charAt(i));
                break;
            }
        }

        i++;

        for (; i < s1.length() && i < s2.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 == c2 && (c1 >= 'A' && c1 <= 'N' || Character.isDigit(c1))) {
                ans.append(s1.charAt(i));
                break;
            }
        }


        return ans.toString();
    }

    private static int findCommonChar(String s1, String s2) {
        for (int i = 0; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i) && Character.isLetter(s1.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        init();
        String week1 = Read.next();
        String week2 = Read.next();
        String hour1 = Read.next();
        String hour2 = Read.next();
        String week = findCommonSub(week1, week2);
        System.out.print(weeks.get(week.charAt(0)) + " ");
        System.out.println(String.format("%02d", findHour(week.charAt(1))) + ":" + String.format("%02d", findCommonChar(hour1, hour2)));
    }
}
