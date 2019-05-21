import util.Read;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class FriendNumbers {
    private static int sum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            set.add(sum(Read.nextInt()));
        }
        System.out.println(set.size());
        System.out.println(set.toString().replaceAll("[\\[\\],]", ""));
    }
}
