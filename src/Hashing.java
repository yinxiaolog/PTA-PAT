import util.Read;

import java.io.IOException;

public class Hashing {
    private static boolean[] hashTable;

    private static boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }

        if (x == 2 || x == 3) {
            return true;
        }
        int sqrt = (int)Math.sqrt(x) + 1;

        for (int i = 2; i < sqrt; i++) {
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static int findPrime(int x) {
        while (!isPrime(x)) {
            x++;
        }
        return x;
    }

    private static String hashCode(int n) {
        int hash = n % hashTable.length;
        if (!hashTable[hash]) {
            hashTable[hash] = true;
            return String.valueOf(hash);
        } else {
            int hashTmp = 0;
            for (int i = 1; i <= hashTable.length; i++) {
                hashTmp = (hash + i * i) % hashTable.length;
                if (!hashTable[hashTmp]) {
                    hashTable[hashTmp] = true;
                    return String.valueOf(hashTmp);
                }
            }
            return "-";
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int M = Read.nextInt();
        int N = Read.nextInt();

        M = findPrime(M);
        hashTable = new boolean[M];

        for (int i = 0; i < N -1; i++) {
            System.out.print(hashCode(Read.nextInt()) + " ");
        }
        System.out.println(hashCode(Read.nextInt()));
    }
}
