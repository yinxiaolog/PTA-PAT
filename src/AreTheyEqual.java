import util.Read;

import java.io.IOException;

public class AreTheyEqual {
    private static int N;

    private static class FloatNum {
        String number;
        String significantFigure;
        int exponent;

        public FloatNum(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            if (number.replaceFirst("^0*", "").length() == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append('0').append('.');
                for (int i = 0; i < N; i++) {
                    sb.append('0');
                }
                sb.append('*');
                sb.append("10");
                sb.append('^');
                sb.append(exponent);
                return sb.toString();
            }
            StringBuilder sb = new StringBuilder();
            sb.append('0').append('.');
            sb.append(significantFigure);
            sb.append('*');
            sb.append("10");
            sb.append('^');
            sb.append(exponent);
            return sb.toString();
        }
    }

    private static void float2ScientificNotation(FloatNum floatNum) {
        String[] strings = floatNum.number.split("\\.");
        if (strings.length == 1 && strings[0].replaceFirst("^0*", "").length() == 0) {
            floatNum.significantFigure = "0";
            floatNum.exponent = 0;
            return;
        }

        if (strings.length == 2 && strings[0].replaceFirst("^0*", "").length() == 0
                && strings[1].replaceFirst("^0*", "").length() == 0) {
            floatNum.significantFigure = "0";
            floatNum.exponent = 0;
            return;
        }
        strings[0] = strings[0].replaceFirst("^0*", "");
        if (strings[0].length() == 0 || strings[0].equals("")) {
            int count = 0;
            for (int i = 0; i < strings[1].length(); i++) {
                if (strings[1].charAt(i) == '0') {
                    count--;
                }
            }

            floatNum.exponent = count;

        } else {
            floatNum.exponent = strings[0].length();
        }

        StringBuilder sb = new StringBuilder();
        if (strings[0].length() >= N) {
            for (int i = 0; i < N; i++) {
                sb.append(strings[0].charAt(i));
            }
        }

        if (strings[0].length() > 0 && strings[0].length() < N) {
            for (int i = 0; i < strings[0].length(); i++) {
                sb.append(strings[0].charAt(i));
            }

            if (strings.length == 1) {
                for (int i = 0; i < N - strings[0].length(); i++) {
                    sb.append('0');
                }
            } else {
                for (int i = 0; i < N - strings[0].length(); i++) {
                    sb.append(strings[1].charAt(i));
                }
            }
        }

        if (strings[0].length() == 0 || strings[0].equals("")) {
            String tmp = strings[1].replaceFirst("^0*", "");
            for (int i = 0; i < N; i++) {
                sb.append(tmp.charAt(i));
            }
        }

        floatNum.significantFigure = sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        N = Read.nextInt();

        String numA = Read.next();
        String numB = Read.next();
        FloatNum floatNumA = new FloatNum(numA);
        FloatNum floatNumB = new FloatNum(numB);

        float2ScientificNotation(floatNumA);
        float2ScientificNotation(floatNumB);
        if (floatNumA.significantFigure.equals(floatNumB.significantFigure)
                && floatNumA.exponent == floatNumB.exponent) {
            System.out.println("YES" + " " + floatNumA);
        } else {
            System.out.println("NO" + " " + floatNumA + " " + floatNumB);
        }
    }
}
