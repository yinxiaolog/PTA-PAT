#include <iostream>
#include <string>
#include <unordered_map>

using namespace std;

string unit_digit[13] = {"tret", "jan", "feb", "mar", "apr", "may", "jun", "jly", "aug", "sep", "oct", "nov", "dec"};
string higher_digit[13] = {"tret", "tam", "hel", "maa", "huh", "tou", "kes", "hei", "elo", "syy", "lok", "mer", "jou"};

unordered_map<string, int> mars2earth;

void Init() {
    for (int i = 0; i < 13; i++) {
        mars2earth[unit_digit[i]] = i;
    }

    for (int i = 1; i < 13; i++) {
        mars2earth[higher_digit[i]] = 13 * i;
    }
}

string TranslateEarth(int x) {
    int unit = x % 13;
    int higher = x / 13;
    string ans;

    if (higher > 0 && unit == 0) {
        ans += higher_digit[higher];
        return ans;
    }

    if (higher > 0) {
        ans += higher_digit[higher];
        ans += " ";
    }

    ans += unit_digit[unit];
    return ans;
}

int TranslateMars(string mars) {
    if (mars.length() > 3) {
        string unit = mars.substr(4, 7);
        string higher = mars.substr(0, 3);
        return mars2earth[higher] + mars2earth[unit];
    } else {
        return mars2earth[mars];
    }
}

int main() {
    int N;
    cin >> N;
    getchar();
    Init();
    for (int i = 0; i < N; i++) {
        string str;
        getline(cin, str);
        if (str[0] <= '9' && str[0] >= '0') {
            cout << TranslateEarth(stoi(str)) << endl;
        } else {
            cout << TranslateMars(str) << endl;
        }
    }
}