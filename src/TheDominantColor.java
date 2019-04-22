import util.Read;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TheDominantColor {
    public static void main(String[] args) throws IOException {
        Read.init();
        int M = Read.nextInt();
        int N = Read.nextInt();
        Map<Integer, Integer> map = new HashMap<>();

        int half = M * N / 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int color = Read.nextInt();
                if (map.get(color) == null) {
                    map.put(color, 1);
                } else {
                    map.put(color, map.get(color) + 1);
                }
                if (map.get(color) > half) {
                    System.out.println(color);
                    return;
                }
            }
        }
    }
}
