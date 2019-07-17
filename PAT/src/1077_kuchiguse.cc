#include <iostream>
#include <string>
#include <algorithm>
#include <cstdio>

using namespace std;

const int kSize = 100;

int N;
string lines[kSize];

bool IsEquals(int index) {
    for (int i = 0; i < N - 1; i++) {
        if (lines[i][index] != lines[i + 1][index]) {
            return false;
        }
    }

    return true;
}

int main() {
    scanf("%d\n", &N);
    int min_len = 256;
    for (int i = 0; i < N; i++) {
        string str;
        getline(cin, str);
        reverse(str.begin(), str.end());
        lines[i] = str;
        if (lines[i].length() < min_len) {
            min_len = lines[i].length();
        }
    }

    string ans;
    for (int i = 0; i < min_len; i++) {
        if (IsEquals(i)) {
            ans += lines[0][i];
        } else {
            break;
        }
    }

    if (ans.empty()) {
        cout << "nai";
    } else {
        reverse(ans.begin(), ans.end());
        cout << ans;
    }
    return 0;
}