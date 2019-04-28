import util.Read;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SetSimilarity {
    private static Set<Integer>[] sets;

    private static String similarity(Set<Integer> setA, Set<Integer> setB) {
        Set<Integer> intersection = new HashSet<>();
        intersection.addAll(setA);
        intersection.retainAll(setB);

        double rate = (double)intersection.size() / (double)(setA.size() + setB.size() - intersection.size()) * 100;
        return String.format("%.1f", rate) + '%';
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        sets = new Set[N];

        for (int i = 0; i < N; i++) {
            int M = Read.nextInt();
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < M; j++) {
                set.add(Read.nextInt());
            }
            sets[i] = set;
        }

        int K = Read.nextInt();
        for (int i = 0; i < K; i++) {
            System.out.println(similarity(sets[Read.nextInt() - 1], sets[Read.nextInt() - 1]));
        }
    }
}
