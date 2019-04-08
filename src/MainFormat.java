import util.Read;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainFormat {
    public static void main(String[] args) throws IOException {
        /**
        Read.init();
        int a = Read.nextInt();
        int b = Read.nextInt();
        System.out.println(format2(a, b));
         */
        Read.init();
        String s = Read.nextLine();
        System.out.println(s);
    }

    public static String format(int a, int b) {
        int sum = a + b;
        String s = String.valueOf(Math.abs(sum));
        StringBuffer ans = new StringBuffer(s);
        for (int i = ans.length(); ans.length() > 3 && i > 0; i -= 3) {
            ans.insert(i, ",");
        }
        if (ans.charAt(ans.length() - 1) == ',') {
            ans.deleteCharAt(ans.length() - 1);
        }
        if (sum >= 0) {
            return ans.toString();
        } else {
            return "-" + ans.toString();
        }
    }

    public static String format1(int a, int b) {
        int sum = a + b;
        NumberFormat numberFormat = new DecimalFormat("###,###,###");
        return numberFormat.format(sum);
    }

    public static String format2(int a, int b) {
        int sum = a + b;
        String ans = "";
        if (sum < 0) {
            sum = -sum;
            ans += "-";
        }

        if (sum >= 1000000) {

        }
        return ans;
    }
}