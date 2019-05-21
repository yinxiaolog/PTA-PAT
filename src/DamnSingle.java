import util.Read;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DamnSingle {
    private static Map<Integer, Integer> couples1 = new HashMap<>();
    private static Map<Integer, Integer> couples2 = new HashMap<>();
    private static Set<Integer> set = new HashSet<>();

    private static Set<String> solve() {
        Set<String> ans = new TreeSet<>();

        for (Integer i : set) {
            if (!set.contains(couples1.get(i))) {
                ans.add(String.format("%05d", i));
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        for (int i = 0; i < N; i++) {
            int id1 = Read.nextInt();
            int id2 = Read.nextInt();
            couples1.put(id1, id2);
            couples1.put(id2, id1);
        }

        int M = Read.nextInt();
        for (int i = 0; i < M; i++) {
            set.add(Read.nextInt());
        }

        Set<String> ans = solve();
        System.out.println(ans.size());
        if (ans.size() != 0) {
            System.out.println(ans.toString().replaceAll("[\\[\\],]", ""));
        }
    }
}
