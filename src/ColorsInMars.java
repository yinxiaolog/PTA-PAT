import util.Read;

import java.io.IOException;
import java.util.Stack;

public class ColorsInMars {
    private static final String radix13 = "0123456789ABC";

    private static String decimal2Radix13(int decimal) {
        if (decimal == 0) {
            return "00";
        }
        Stack<Integer> stack = new Stack<>();
        while (decimal > 0) {
            stack.push(decimal % 13);
            decimal /= 13;
        }

        String s = "";
        while (!stack.isEmpty()) {
            s += String.valueOf(radix13.charAt(stack.pop()));
        }

        if (s.length() == 1) {
            s = "0" + s;
        }

        return s;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        String R = decimal2Radix13(Read.nextInt());
        String G = decimal2Radix13(Read.nextInt());
        String B = decimal2Radix13(Read.nextInt());
        System.out.println("#" + R + G + B);
    }
}
