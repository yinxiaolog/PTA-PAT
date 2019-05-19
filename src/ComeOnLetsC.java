import util.Read;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ComeOnLetsC {
    private static boolean isPrime(int x) {
        int sqrt = (int) Math.sqrt(x);
        for (int i = 2; i <= sqrt; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int[] rank = new int[10000];
        for (int i = 0; i < rank.length; i++) {
            rank[i] = -1;
        }

        for (int i = 1; i <= N; i++) {
            rank[Read.nextInt()] = i;
        }

        Map<Integer, Integer> map = new HashMap<>();

        int K = Read.nextInt();
        for (int i = 0; i < K; i++) {
            int id = Read.nextInt();
            if (map.containsKey(id)) {
                if (rank[id] == -1) {
                    System.out.printf("%04d: %s\n", id, "Are you kidding?");
                } else {
                    System.out.printf("%04d: %s\n", id, "Checked");
                }
            } else {
                map.put(id, id);
                if (rank[id] == 1) {
                    System.out.printf("%04d: %s\n", id, "Mystery Award");
                } else if (rank[id] == -1) {
                    System.out.printf("%04d: %s\n", id, "Are you kidding?");
                } else if (isPrime(rank[id])) {
                    System.out.printf("%04d: %s\n", id, "Minion");
                } else {
                    System.out.printf("%04d: %s\n", id, "Chocolate");
                }
            }
        }
    }
}
