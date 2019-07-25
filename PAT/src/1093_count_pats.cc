#include <iostream>
#include <string>

using namespace std;

const int MAX = 1000000007;
const int kSize = 100001;

int p[kSize] = {0};
int t[kSize] = {0};

int main() {
    string str;
    cin >> str;

    if (str[0] == 'P') {
        p[0] = 1;
    } else {
        p[0] = 0;
    }
    for (int i = 1; i < str.length(); i++) {
        if (str[i] == 'P') {
            p[i] = p[i - 1] + 1;
        } else {
            p[i] = p[i - 1];
        }
    }

    if (str[str.length() - 1] == 'T') {
        t[str.length() - 1] = 1;
    } else {
        t[str.length() - 1] = 0;
    }
    for (int i = str.length() - 2; i >= 0; i--) {
        if (str[i] == 'T') {
            t[i] = t[i + 1] + 1;
        } else {
            t[i] = t[i + 1];
        }
    }

    long long ans = 0;
    for (int i = 0; i < str.length(); i++) {
        if (str[i] == 'A') {
            ans = (ans + p[i] * t[i]) % MAX;
        }
    }
    cout << ans;
    return 0;
}