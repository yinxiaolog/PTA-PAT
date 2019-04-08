import util.Read;

import java.io.IOException;

public class HaveFunWithNumbers {
    private static int[] number;
    private static int[] visit = new int[10];
    private static int[] sum;

    private static void add() {
        int sumIndex = sum.length - 1;
        for (int i = number.length - 1; i >= 0; i--) {
            int sumi = number[i] * 2;
            sum[sumIndex] = sumi % 10 + sum[sumIndex];
            sum[sumIndex - 1] = sumi / 10;
            sumIndex--;
        }
    }

    private static void init(String s) {
        for (int i = 0; i < s.length(); i++) {
            visit[Integer.parseInt(String.valueOf(s.charAt(i)))]++;
        }

        int numberLength = number.length - 1;
        for (int i = 0; i < number.length; i++) {
            number[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
        }
    }

    private static boolean isPermution() {
        add();

        if (sum[0] == 1) {
            return false;
        }
        for (int i = 1; i < sum.length; i++) {
            visit[sum[i]]--;
        }
        boolean isFlag = true;
        for (int i = 0; i < visit.length; i++) {
            if (visit[i] != 0) {
                isFlag = false;
            }
        }
        return isFlag;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        String s = Read.next();
        number = new int[s.length()];
        sum = new int[s.length() + 1];
        if (s.length() == 1 && Integer.parseInt(String.valueOf(s.charAt(0))) == 0) {
            System.out.println("Yes");
            System.out.println(0);
            return;
        }
        init(s);
        if (isPermution() == true) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        if (sum[0] == 1) {
            for (int i = 0; i < sum.length; i++) {
                System.out.print(sum[i]);
            }
        } else {
            for (int i = 1; i < sum.length; i++) {
                System.out.print(sum[i]);
            }
        }
    }
}
