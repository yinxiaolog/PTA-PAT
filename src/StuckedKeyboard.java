import util.Read;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class StuckedKeyboard {
    private static String replace(String str, char c, int i) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sb.append(c);
        }

        return str.replaceAll(sb.toString(), String.valueOf(c));
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int K = Read.nextInt();
        String str = Read.next();
        Set<Character> set = new LinkedHashSet<>();
        Map<Character, Boolean> map = new LinkedHashMap<>();

        for (int i = 0; i < str.length(); i++) {
            int count = 1;
            while (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            if (!map.containsKey(str.charAt(i)) && str.charAt(i) != '_') {
                if (count % K == 0) {
                    map.put(str.charAt(i), false);
                } else {
                    map.put(str.charAt(i), true);
                }
            }
        }

        StringBuilder stuchedKey = new StringBuilder();
        for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if (!entry.getValue()) {
                stuchedKey.append(entry.getKey());
                str = replace(str, entry.getKey(), K);
            }
        }

        System.out.println(stuchedKey);
        System.out.println(str);
    }
}
