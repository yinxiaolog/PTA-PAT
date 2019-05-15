import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindingAverage {
    private static boolean isInt(String str) {
        String re = "[0-9]+";
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private static boolean isDecimal(String str) {
        String[] strings = str.split("\\.");

        if (strings.length == 1 && strings[0].length() <= 2) {
            return true;
        }

        if (strings.length == 1 && strings[0].length() > 2) {
            return false;
        }

        if (strings.length == 2 && strings[1].length() <= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        List<Double> legal = new ArrayList<>();
        List<String> illegal = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = Read.next();
            if (isInt(str) || isDecimal(str)) {
                double x = Double.valueOf(str);
                if (x <= 1000 && x >= -1000) {
                    legal.add(x);
                } else {
                    illegal.add(str);
                }
            } else {
                illegal.add(str);
            }
        }

        for (String s : illegal) {
            System.out.printf("ERROR: %s is not a legal number\n", s);
        }

        if (legal.size() == 0) {
            System.out.println("The average of 0 numbers is Undefined");
        }

        if (legal.size() == 1) {
            System.out.printf("The average of 1 number is %.2f", legal.get(0));
        }

        if (legal.size() > 1) {
            double sum = 0;
            for (Double d : legal) {
                sum += d;
            }

            System.out.printf("The average of %d numbers is %.2f", legal.size(), sum / legal.size());
        }
    }
}
