#include <iostream>
#include <cmath>
#include <cstdio>

using namespace std;

int Gcd(long long a, long long b) {
    if (b == 0) {
        return a;
    }

    return Gcd(b, a % b);
}

struct RationalNumber {
    long long numerator;
    long long denominator;

    RationalNumber() {}

    RationalNumber(long long _numerator, long long _denominator) {
        if (_numerator == 0) {
            numerator = 0;
            denominator = _denominator;
        } else if (_numerator * _denominator < 0) {
            numerator = -abs(_numerator);
            denominator = abs(_denominator);
        } else if (_numerator * _denominator > 0) {
            numerator = abs(_numerator);
            denominator = abs(_denominator);
        }

        Simplify();
    }

    void Simplify() {
        if (numerator == 0) {
            return;
        }
        long gcd = Gcd(abs(numerator), abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
    }
};

RationalNumber Add(RationalNumber one, RationalNumber another) {
    long numerator = one.numerator * another.denominator + another.numerator * one.denominator;
    long denominator = one.denominator * another.denominator;
    return RationalNumber(numerator, denominator);
}

int main() {
    int N;
    cin >> N;
    long numerator, denominator;
    scanf("%lld/%lld", &numerator, &denominator);
    RationalNumber ans = RationalNumber(numerator, denominator);

    for (int i = 1; i < N; i++) {
        scanf("%lld/%lld", &numerator, &denominator);
        RationalNumber tmp = RationalNumber(numerator, denominator);
        ans = Add(ans, tmp);
    }

    if (ans.numerator % ans.denominator == 0) {
        cout << ans.numerator / ans.denominator;
        return 0;
    }

    if (ans.numerator / ans.denominator > 0) {
        cout << ans.numerator / ans.denominator << " " << ans.numerator % ans.denominator << '/' << ans.denominator;
    } else {
        cout << ans.numerator << '/' << ans.denominator;
    }
    return 0;
}