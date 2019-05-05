import util.Read;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class BrokenKeyboard {
    private static boolean[] chars = new boolean[256];

    private static void hash(String str) {
        for (int i = 0; i < str.length(); i++) {
            chars[str.charAt(i)] = true;
        }
    }

    private static String getMissKey(String intact) {
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new LinkedHashSet<>();
        for (int i = 0; i < intact.length(); i++) {
            if (!chars[intact.charAt(i)]) {
                set.add(intact.charAt(i));
            }
        }

        for (Character c : set) {
            sb.append(c);
        }

        return sb.toString().toUpperCase();
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        String intact = Read.next().toUpperCase();
        String missing = Read.next().toUpperCase();

        hash(missing);

        System.out.println(getMissKey(intact));
    }
}
