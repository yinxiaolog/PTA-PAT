import util.Read;

import java.io.IOException;

public class RationalArithmetic {
    private static class Rational {
        long numerators;
        long denominators;

        public Rational(long numerators, long denominators) {
            if (numerators == 0) {
                return;
            }
            long gcd = GCD(numerators, denominators);
            this.numerators = numerators / gcd;
            this.denominators = denominators / gcd;
        }

        @Override
        public String toString() {
            if (numerators == 0) {
                return "0";
            }

            StringBuilder sb = new StringBuilder();
            if (numerators * denominators < 0) {
                sb.append('-');
            }

            long intPart = numerators / denominators;
            if (intPart != 0) {
                sb.append(Math.abs(intPart)).append(' ');
                if (numerators % denominators == 0) {
                    if (numerators * denominators < 0) {
                        return '(' + sb.toString().trim() + ')';
                    } else {
                        return sb.toString().trim();
                    }
                }
            }

            sb.append(Math.abs(numerators - intPart * denominators));
            sb.append('/');
            sb.append(Math.abs(denominators));
            if (numerators * denominators < 0) {
                return '(' + sb.toString().trim() + ')';
            } else {
                return sb.toString();
            }
        }
    }

    private static long GCD(long x, long y) {
        return x % y == 0 ? y : GCD(y, x % y);
    }

    private static String add(Rational rationalA, Rational rationalB) {
        if (rationalA.numerators == 0 && rationalB.numerators == 0) {
            return 0 + " + " + 0 + " = " + 0;
        }

        if (rationalA.numerators == 0) {
            return rationalA.toString() + " + " + rationalB.toString() + " = " + rationalB.toString();
        }

        if (rationalB.numerators == 0) {
            return rationalA.toString() + " + " + rationalB.toString() + " = " + rationalA.toString();
        }

        long gcd = GCD(rationalA.denominators, rationalB.denominators);
        long lcm = rationalA.denominators * rationalB.denominators / gcd;

        long numerators = rationalA.numerators * lcm / rationalA.denominators +
                rationalB.numerators * lcm / rationalB.denominators;
        long denominators = lcm;
        Rational ans = new Rational(numerators, denominators);
        return rationalA.toString() + " + " + rationalB.toString() + " = " + ans.toString();
    }

    private static String sub(Rational rationalA, Rational rationalB) {
        if (rationalA.numerators == 0 && rationalB.numerators == 0) {
            return 0 + " - " + 0 + " = " + 0;
        }

        if (rationalA.numerators == 0) {
            Rational ans = new Rational(-rationalB.numerators, rationalB.denominators);
            return rationalA.toString() + " - " + rationalB.toString() + " = " + ans.toString();
        }

        if (rationalB.numerators == 0) {
            return rationalA.toString() + " - " + rationalB.toString() + " = " + rationalA.toString();
        }

        long gcd = GCD(rationalA.denominators, rationalB.denominators);
        long lcm = rationalA.denominators * rationalB.denominators / gcd;

        long numerators = rationalA.numerators * lcm / rationalA.denominators -
                rationalB.numerators * lcm / rationalB.denominators;
        long denominators = lcm;
        Rational ans = new Rational(numerators, denominators);
        return rationalA.toString() + " - " + rationalB.toString() + " = " + ans.toString();
    }

    private static String mul(Rational rationalA, Rational rationalB) {
        Rational ans = new Rational(rationalA.numerators * rationalB.numerators,
                rationalA.denominators * rationalB.denominators);
        return rationalA.toString() + " * " + rationalB.toString() + " = " + ans.toString();
    }

    private static String div(Rational rationalA, Rational rationalB) {
        if (rationalB.numerators == 0) {
            return rationalA.toString() + " / " + rationalB.toString() + " = " + "Inf";
        }

        Rational ans = new Rational(rationalA.numerators * rationalB.denominators,
                rationalB.numerators * rationalA.denominators);
        return rationalA.toString() + " / " + rationalB.toString() + " = " + ans.toString();
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        String str1 = Read.next();
        String str2 = Read.next();
        String[] strings1 = str1.split("/");
        String[] strings2 = str2.split("/");
        long numerators = Integer.valueOf(strings1[0]);
        long denominators = Integer.valueOf(strings1[1]);
        Rational rationalA = new Rational(numerators, denominators);
        numerators = Integer.valueOf(strings2[0]);
        denominators = Integer.valueOf(strings2[1]);
        Rational rationalB = new Rational(numerators, denominators);
        System.out.println(add(rationalA, rationalB));
        System.out.println(sub(rationalA, rationalB));
        System.out.println(mul(rationalA, rationalB));
        System.out.println(div(rationalA, rationalB));
    }
}
