#include <iostream>
#include <vector>
#include <stack>
#include <string>

using namespace std;

const string kRadix13 = "0123456789ABC";

string Decimal2RadixN(int val, int radix) {
    if (val == 0) {
        return "00";
    }
    stack<int> s;

    while (val > 0) {
        s.push(val % radix);
        val /= radix;
    }

    string ans;
    while (!s.empty()) {
        ans += kRadix13[s.top()];
        s.pop();
    }

    if (ans.size() == 1) {
        ans = "0" + ans;
    }

    return ans;
}

int main() {
    string ans = "#";
    for (int i = 0; i < 3; i++) {
        int n;
        cin >> n;
        ans += Decimal2RadixN(n, 13);
    }

    cout << ans;
}