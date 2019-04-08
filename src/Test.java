public class Test{
    public static String int2String(int time) {
        int h = time / 3600;
        String hh = String.format("%02d", h);
        time = time % 3600;
        int m = time / 60;
        String mm = String.format("%02d", m);
        h = time % 60;
        int s = h;
        String ss = String.format("%02d", s);
        return hh + ":" + mm + ":" + ss;
    }

    private static int string2Int(String time) {
        String[] HHMMSS = time.split(":");
        int h = Integer.parseInt(HHMMSS[0]) * 3600;
        int m = Integer.parseInt(HHMMSS[1]) * 60;
        int s = Integer.parseInt(HHMMSS[2]);
        return h + m + s;
    }
    public static void main(String[] args) {
        String s = "1234";
        System.out.println(s);
        System.out.println(s.replace('1','@'));
        System.out.println(s);
    }
}