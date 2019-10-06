#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

bool IsPalindrome(string &s) {
    for (int i = 0, j = s.length() - 1; i <= j; ++i, --j) {
        if (s[i] != s[j]) {
            return false;
        }
    }

    return true;
}

string Add(string &s) {
    string ans;
    int carry = 0;
    for (int i = 0, j = s.length() - 1; i < s.length(); ++i, --j) {
        int sum = (s[i] - '0') + (s[j] - '0') + carry;
        if (sum >= 10) {
            carry = sum / 10;
            sum = sum % 10;
        } else {
            carry = 0;
        }

        ans = to_string(sum) + ans;
    }

    if (carry > 0) {
        ans = to_string(carry) + ans;
    }

    return ans;
}

int main() {
    string s;
    cin >> s;

    if (IsPalindrome(s)) {
        cout << s << " is a palindromic number." << endl;
        return 0;
    }

    for (int i = 0; i < 10; ++i) {
        string re = s;
        reverse(re.begin(), re.end());
        string sum = Add(s);
        cout << s << " + " << re << " = " << sum << endl;
        s = sum;
        if (IsPalindrome(sum)) {
            cout << sum << " is a palindromic number." << endl;
            return 0;
        }
    }

    cout << "Not found in 10 iterations." << endl;
}