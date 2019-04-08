import util.Read;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class MainReversiblePrimes {
    public static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int reverse(int num, int radix) {
        Queue<Integer> queue = new LinkedList<>();
        while (num != 0) {
            queue.add(num % radix);
            num /= radix;
        }

        double sum = 0;
        while (!queue.isEmpty()) {
            sum = sum + queue.remove() * Math.pow(radix, queue.size());
        }
        return (int) sum;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int num;
        int radix;
        String s = "";
        while (true) {
            num = Read.nextInt();
            if (num < 0) {
                break;
            }

            radix = Read.nextInt();
            if (radix < 0) {
                break;
            }
            if (isPrime(num) && isPrime(reverse(num, radix))) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}