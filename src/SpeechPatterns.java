import util.Read;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpeechPatterns {
    private static Map<String, Integer> stringCount(String s) {
        s= s.replaceAll("[^0-9A-Za-z]", " ");
        String[] strings = s.split("\\s+");
        Map<String, Integer> ans = new HashMap<>();

        for (String string : strings) {
            string = string.toLowerCase();
            if (ans.get(string) == null) {
                ans.put(string, 1);
            } else {
                ans.put(string, ans.get(string) + 1);
            }
        }
        return ans;
    }

    private static String maxCount(Map<String, Integer> map) {
        String s = "";
        int count = 0;

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > count) {
                count = entry.getValue();
                s = entry.getKey();
            } else if (entry.getValue() == count) {
                if (entry.getKey().compareTo(s) < 0) {
                    s = entry.getKey();
                } else {
                    s = s;
                }
            }
        }

        return s + " " + count;
    }
    public static void main(String[] args) throws IOException {
        Read.init();
        String s = Read.nextLine();
        System.out.println(maxCount(stringCount(s)));
    }
}
