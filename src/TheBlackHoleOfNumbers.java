import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class TheBlackHoleOfNumbers {
    private static int max(int number) {
        int[] arr = new int[4];
        arr[0] = number % 10;
        number /= 10;
        arr[1] = number % 10;
        number /= 10;
        arr[2] = number % 10;
        arr[3] = number / 10;
        Arrays.sort(arr);

        int sum = 0;
        sum += arr[3] * 1000;
        sum += arr[2] * 100;
        sum += arr[1] * 10;
        sum += arr[0];

        return sum;
    }

    private static int min(int number) {
        int[] arr = new int[4];
        arr[0] = number % 10;
        number /= 10;
        arr[1] = number % 10;
        number /= 10;
        arr[2] = number % 10;
        arr[3] = number / 10;
        Arrays.sort(arr);

        int sum = 0;
        sum += arr[0] * 1000;
        sum += arr[1] * 100;
        sum += arr[2] * 10;
        sum += arr[3];

        return sum;
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        String s = String.valueOf(N);
        if (s.length() == 3) {
            s = s + '0';
        }

        if (s.length() == 2) {
            s = s + "00";
        }

        if (s.length() == 1) {
            s = s + "000";
        }

        N = Integer.parseInt(s);

        if (s.charAt(0) == s.charAt(1) && s.charAt(1) == s.charAt(2) && s.charAt(2) == s.charAt(3)){
            System.out.printf("%04d - %04d = %04d\n", N, N, 0);
        } else {
            int max = max(N);
            int min = min(N);
            while (max - min != 6174) {
                System.out.printf("%04d - %04d = %04d\n", max, min, max - min);
                N = max - min;
                max = max(N);
                min = min(N);
            }
            System.out.printf("%04d - %04d = %04d\n", max, min, max - min);
        }
    }
}
