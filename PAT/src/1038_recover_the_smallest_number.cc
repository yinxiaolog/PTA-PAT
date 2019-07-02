#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool Cmp(string a, string b) {
    if (a + b < b + a) {
        return true;
    } else {
        return false;
    }
}

int main() {
    int N;
    vector<string> nums;
    cin >> N;
    for (int i = 0; i < N; i++) {
        string tmp;
        cin >> tmp;
        nums.push_back(tmp);
    }

    sort(nums.begin(), nums.end(), Cmp);
    string ans;
    for (string &s : nums) {
        ans += s;
    }

    while (ans.length() != 0 && ans[0] == '0') {
        ans.erase(ans.begin());
    }

    if (ans.empty()) {
        cout << 0;
    } else {
        cout << ans;
    }

    return 0;
}