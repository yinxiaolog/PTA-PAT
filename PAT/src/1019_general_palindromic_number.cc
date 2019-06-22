#include <iostream>
#include <vector>

using namespace std;

bool IsPalindromic(const vector<int> &v) {
    for (int i = 0; i < v.size() / 2; i++) {
        if (v[i] != v[v.size() - 1 - i]) {
            return false;
        }
    }

    return true;
}

vector<int> Reversal(int n, int radix) {
    vector<int> ans;
    while (n > 0) {
        ans.push_back(n % radix);
        n /= radix;
    }

    return ans;
}

int main() {
    int N, b;
    cin >> N >> b;
    vector<int> ans = Reversal(N, b);
    if (IsPalindromic(ans)) {
        cout << "Yes" << endl;
    } else {
        cout << "No" << endl;
    }

    for (int i = ans.size() - 1; i >= 0; i--) {
        if (i != 0) {
            cout << ans[i] << " ";
        } else {
            cout << ans[i];
        }
    }
    return 0;
}