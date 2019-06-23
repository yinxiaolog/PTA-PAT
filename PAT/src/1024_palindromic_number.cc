#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

bool IsPalindromicNumber(const string &str) {
    for (int i = 0; i < str.length() / 2; i++) {
        if (str[i] != str[str.length() - 1 - i]) {
            return false;
        }
    }

    return true;
}

string Add(const string &s1, const string &s2) {
    string ans;
    int carry_bit = 0;
    for (int i = s1.length() - 1; i >= 0; i--) {
        int add = s1[i] - '0' + s2[i] - '0' + carry_bit;
        if (add >= 10) {
            carry_bit = 1;
            add -= 10;
        } else {
            carry_bit = 0;
        }

        ans.insert(ans.size(), to_string(add));
    }

    if (carry_bit == 1) {
        ans.insert(ans.size(), to_string(carry_bit));
    }

    reverse(ans.begin(), ans.end());
    return ans;
}

int main() {
    string N;
    int K;
    cin >> N >> K;

    if (IsPalindromicNumber(N)) {
        cout << N << endl;
        cout << 0;
        return 0;
    }

    for (int i = 1; i <= K; i++) {
        string rN = N;
        reverse(rN.begin(), rN.end());
        N = Add(N, rN);
        if (IsPalindromicNumber(N) || i == K) {
            cout << N << endl;
            cout << i;
            break;
        }
    }

    return 0;
}