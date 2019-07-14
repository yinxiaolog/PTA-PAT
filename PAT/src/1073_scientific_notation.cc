#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int main() {
    string str;
    cin >> str;

    char sign = str[0];
    str.erase(0, 1);

    string coe, exp;
    bool flag = false;
    for (int i = 0; i < str.length(); i++) {
        if (str[i] == 'E') {
            flag = true;
        }

        if (str[i] != 'E') {
            if (!flag) {
                coe += str[i];
            } else {
                exp += str[i];
            }
        }
    }

    int exp_int = stoi(exp);

    if (exp_int == 0) {
        if (sign == '+') {
            cout << coe;
        } else {
            cout << sign + coe;
        }
        return 0;
    }

    int len = coe.length();
    if (exp_int > 0) {
        if (exp_int < coe.length() - 2) {
            coe.insert(2 + exp_int, ".");
        }
        for (int i = 0; i < exp_int - len + 2; i++) {
            coe += '0';
        }
    }

    if (exp_int < 0) {
        for (int i = 0; i < abs(exp_int); i++) {
            coe = '0' + coe;
        }
    }

    coe.erase(coe.find('.'), 1);

    if (exp_int > 0) {
        if (sign == '+') {
            cout << coe;
        } else {
            cout << sign + coe;
        }
    } else {
        coe.insert(1, ".");
        if (sign == '+') {
            cout << coe;
        } else {
            cout << sign + coe;
        }
    }
    return 0;
}