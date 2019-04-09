import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class MagicCoupon {
    public static void main(String[] args) throws IOException {
        Read.init();
        int Nc = Read.nextInt();
        int[] coupons = new int[Nc];
        for (int i = 0; i < Nc; i++) {
            coupons[i] = Read.nextInt();
        }

        int Np = Read.nextInt();
        int[] products = new int[Np];
        for (int i = 0; i < Np; i++) {
            products[i] = Read.nextInt();
        }

        Arrays.sort(coupons);
        Arrays.sort(products);
        long maxMoney = 0;
        for (int i = 0; i < coupons.length && i < products.length; i++) {
            if (coupons[i] * products[i] < 0) {
                break;
            }

            if (coupons[i] < 0 && products[i] < 0) {
                maxMoney += coupons[i] * products[i];
            }
        }

        for (int i = coupons.length - 1, j = products.length - 1; i >=0 && j >= 0; i--, j--) {
            if (coupons[i] * products[j] < 0) {
                break;
            }

            if (coupons[i] > 0 && products[j] > 0) {
                maxMoney += coupons[i] * products[j];
            }
        }
        System.out.println(maxMoney);
    }
}
