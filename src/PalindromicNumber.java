import util.Read;

import java.io.IOException;

public class PalindromicNumber {
    private static String N = null;
    private static String ans = null;
    private static int K;

    private static boolean isPalindromic(String number) {
        String reverse = new StringBuffer(number).reverse().toString();
        return number.equals(reverse);
    }

    private static String add(String number) {
        int[] sum = new int[number.length() + 1];
        int index = sum.length - 1;
        String reverse = new StringBuffer(number).reverse().toString();
        for (int i = 0; i < number.length(); i++) {
            int sumOfIj = Integer.parseInt(String.valueOf(number.charAt(i))) +
                    Integer.parseInt(String.valueOf(reverse.charAt(i)));
            //System.out.println(sumOfIj);
            sum[index - 1] = (sumOfIj + sum[index]) / 10;
            sum[index] = (sumOfIj + sum[index]) % 10;
            index--;
            //System.out.println(Arrays.toString(sum));
        }

        StringBuffer sb = new StringBuffer();
        if (sum[0] == 0){
            for (int i = 1; i < sum.length; i++) {
                sb.append(sum[i]);
            }
        } else {
            for (int i = 0; i < sum.length; i++)  {
                sb.append(sum[i]);
            }
        }
        return sb.toString();
    }

    private static int calSteps(String number) {
        ans = number;
        for (int i = 0; i <= K; i++) {
            if (isPalindromic(number) || i == K){
                return i;
            }
            number = add(number);
            ans = number;
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("rm -rf /");
        Read.init();
        N = Read.next();
        K = Read.nextInt();
        int steps = calSteps(N);
        System.out.println(ans);
        System.out.println(steps);

        /*String s = "7587155559";
        double a = 17142673416.0;
        double b = 61437624171.0;
        //System.out.println(a + b);
        System.out.println(add("17142673416"));
        */
    }
}
