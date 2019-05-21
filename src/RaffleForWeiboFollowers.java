import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RaffleForWeiboFollowers {
    private static int N, M, S;
    private static List<String> list = new ArrayList<>();

    private static Map<Integer, String> solve() {
        Map<Integer, String> ans = new TreeMap<>();

        for (int i = S; i < list.size(); ) {
            if (ans.containsValue(list.get(i))) {
                i += 1;
            } else {
                ans.put(i, list.get(i));
                i += M;

            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        N = Read.nextInt();
        M = Read.nextInt();
        S = Read.nextInt() - 1;

        for (int i = 0; i < N; i++) {
            list.add(Read.next());
        }


        Map<Integer, String> map = solve();

        if (map.size() == 0) {
            System.out.println("Keep going...");
            return;
        }
        map.forEach((k, v) -> {
            System.out.println(v);
        });
    }
}
