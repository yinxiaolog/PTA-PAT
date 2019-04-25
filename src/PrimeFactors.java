import util.Read;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class PrimeFactors {
    private static boolean[] primes;

    private static void calPrimes(int count) {
        primes = new boolean[count + 1];
        for (int i = 2; i < primes.length; i++) {
            primes[i] = true;
        }

        for (int i = 2; i < primes.length; i++) {
            for (int j = i * 2; j < primes.length; j += i) {
                primes[j] = false;
            }
        }
    }

    private static Map<Integer, Integer> primeFactor(long N) {
        Map<Integer, Integer> ans = new TreeMap<>();

        while (N > 1) {
            for (int i = 0; i < primes.length; i++) {
                if (primes[i]) {
                    if (N % i == 0) {
                        //System.out.println(i);
                        if (ans.get(i) == null) {
                            ans.put(i, 1);
                        } else {
                            ans.put(i, ans.get(i) + 1);
                        }
                        N /= i;
                        //System.out.println("N:" + N);
                        break;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        long N = Read.nextLong();
        if (N == 1) {
            System.out.println("1=1");
            return;
        }
        calPrimes(1000000);
        Map<Integer, Integer> map = primeFactor(N);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                sb.append(entry.getKey()).append('^').append(entry.getValue());
            } else {
                sb.append(entry.getKey());
            }
            sb.append('*');
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(N + "=" + sb);
    }
}
