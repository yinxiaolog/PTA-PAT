import util.Read;

import java.io.IOException;

public class ScientificNotation {
    private static String changeNotation(String scientificNotation) {
        char symbol = scientificNotation.charAt(0);
        scientificNotation = scientificNotation.substring(1);
        String[] strings = scientificNotation.split("[E\\.]");
        String beforeDecimalPoint = strings[0];
        String afterDecimalPoint = strings[1];
        String expStr = strings[2];
        char expSymbol = expStr.charAt(0);
        expStr = expStr.substring(1);
        int exp = Integer.parseInt(expStr);

        StringBuilder ans = new StringBuilder();
        if (expSymbol == '-') {
            ans.append(symbol);
            ans.append("0.");
            for (int i = 0; i < exp - 1; i++) {
                ans.append('0');
            }
            ans.append(beforeDecimalPoint);
            ans.append(afterDecimalPoint);
        } else {
            ans.append(symbol);
            ans.append(beforeDecimalPoint);
            if (exp < afterDecimalPoint.length()) {
                int i = 0;
                for (; i < exp; i++) {
                    ans.append(afterDecimalPoint.charAt(i));
                }
                ans.append('.');
                for (;i < afterDecimalPoint.length(); i++) {
                    ans.append(afterDecimalPoint.charAt(i));
                }
            } else {
                ans.append(afterDecimalPoint);
                for (int i = 0; i < exp - afterDecimalPoint.length(); i++) {
                    ans.append('0');
                }
            }
        }
        if (ans.charAt(0) == '+') {
            return ans.substring(1);
        }
        return ans.toString();
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        String numberA = Read.next();
        System.out.println(changeNotation(numberA));
    }
}
