import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mooncake {
    private static class Cake implements Comparable<Cake>{
        double amounts;
        double totalPrice;
        double price;

        public Cake(double amounts, double totalPrice) {
            this.amounts = amounts;
            this.totalPrice = totalPrice;
            this.price = totalPrice / amounts;
        }

        @Override
        public int compareTo(Cake another) {
            if (price < another.price) {
                return -1;
            }

            if (price > another.price) {
                return 1;
            }

            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int D = Read.nextInt();
        double[] amount = new double[N];
        double[] totalPrice = new double[N];
        for (int i = 0; i < N; i++) {
            amount[i] = Read.nextDouble();
        }

        for (int i = 0; i < N; i++) {
            totalPrice[i] = Read.nextDouble();
        }

        List<Cake> cakeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            cakeList.add(new Cake(amount[i], totalPrice[i]));
        }

        double ans = 0;
        Collections.sort(cakeList);
        for (int i = cakeList.size() - 1; i >=0 && D >= 0; i--) {
            Cake cake = cakeList.get(i);
            if (cake.amounts > D) {
                ans += D * cake.price;
                D -= D;
            } else {
                ans += cake.totalPrice;
                D -= cake.amounts;
            }
        }

        System.out.printf("%.2f", ans);
    }

}
