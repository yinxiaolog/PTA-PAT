import util.Read;

import java.io.IOException;

public class ToBuyOrNotToBuy {
    private static int[] target = new int[256];

    private static void hash(String beads) {
        for (int i = 0; i < beads.length(); i++) {
            target[beads.charAt(i)]++;
        }
    }

    private static int solve(String beads) {
        for (int i = 0; i < beads.length(); i++) {
            if (target[beads.charAt(i)] > 0) {
                target[beads.charAt(i)]--;
            }
        }

        int ans= 0;

        for (int i  = 0; i < target.length; i++) {
            ans += target[i];
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        String beads = Read.next();
        String target = Read.next();
        hash(target);
        int ans = solve(beads);
        if (ans == 0) {
            System.out.println("Yes " + (beads.length()- target.length()));
        } else {
            System.out.println("No " + ans);
        }
    }
}
