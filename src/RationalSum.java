import util.Read;

import java.io.IOException;

public class RationalSum {
    private static long gcd(long a, long b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    private static class Fraction {
        long numerator;
        long denominator;

        public Fraction(long numerator, long denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (numerator == 0) {
                return "0";
            }

            if (numerator < 0) {
                sb.append('-');
            }

            if (numerator / denominator != 0) {
                sb.append(numerator / denominator).append(" ");
                //sb.append(Math.abs(numerator = numerator - (numerator / denominator) * denominator));
            }

            if (numerator % denominator != 0) {
                sb.append(Math.abs(numerator) - Math.abs(numerator) / denominator * denominator);
                sb.append('/');
                sb.append(denominator);
            }

            return sb.toString().trim();
        }
    }

    private static Fraction addFraction(Fraction X, Fraction Y) {
        if (X.numerator == 0) {
            return Y;
        }
        if (Y.numerator == 0) {
            return X;
        }
        long lcm = X.denominator * Y.denominator / gcd(X.denominator, Y.denominator);

        //System.out.println(lcm / X.denominator * X.numerator);
        X.numerator = lcm / X.denominator * X.numerator;
        //System.out.println(X.numerator);
        //System.out.println(X);
        Y.numerator = lcm / Y.denominator * Y.numerator;
        X.denominator = lcm;
        Y.denominator = lcm;
        //System.out.println(X);

        Fraction ans = new Fraction(X.numerator + Y.numerator, lcm);
        long gcd = gcd(ans.numerator, ans.denominator);
        ans.numerator = ans.numerator / gcd;
        ans.denominator = ans.denominator / gcd;

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        Fraction[] fractions = new Fraction[N];
        for (int i = 0; i < N; i++) {
            String str = Read.next();
            String[] strings = str.trim().split("/");
            fractions[i] = new Fraction(Long.parseLong(strings[0]), Long.parseLong(strings[1]));
        }

        Fraction ans = new Fraction(0, 0);
        for (int i = 0; i < fractions.length; i++) {
            ans = addFraction(ans, fractions[i]);
            //System.out.println(ans);
        }

        System.out.println(ans);
    }
}
