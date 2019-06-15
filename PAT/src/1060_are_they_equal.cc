#include <iostream>
#include <string>
#include <regex>

using namespace std;

struct ScientificNotation {
    string a;
    int n;
};

ScientificNotation Transform(string num, int sign) {
    regex pattrn("^0*");
    num = regex_replace(num, pattrn, "");

    ScientificNotation ans;
    string a;
    int n = 0;
    int index = 0;
    if (num[0] == '.') {
        num.erase(num.begin());
        while (num.length() > 0 && num[0] == '0') {
            num.erase(num.begin());
            n--;
        }
    } else {
        for (; index < num.length() && num[index] != '.'; index++) {
            n++;
        }

        if (index < num.length()) {
            num.erase(num.begin() + index);
        }
    }

    if (num.length() == 0) {
        n == 0;
    }

    for (int i = 0; i < sign; i++) {
        if (i < num.length()) {
            a += num[i];
        } else {
            a += '0';
        }
    }

    ans.a = "0." + a;
    ans.n = n;
    return ans;
}

int main() {
    int N;
    string A;
    string B;

    cin >> N >> A >> B;

    ScientificNotation numA = Transform(A, N);
    ScientificNotation numB = Transform(B, N);

    if (numA.a == numB.a && numB.n == numB.n) {
        cout << "YES " << numA.a << "*10^" << numA.n;
    } else {
        cout << "NO " << numA.a << "*10^" << numA.n << " " << numB.a << "*10^" << numB.n;
    }
    return 0;
}