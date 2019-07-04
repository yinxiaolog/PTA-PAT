#include <iostream>
#include <string>

using namespace std;
const int kSize = 1001;

int main() {
    string str;
    getline(cin, str);

    bool dp[1001][1001] = {false};
    int ans = 1;
    for (int i = 0; i < str.length(); i++) {
        dp[i][i] = true;
        if (i < str.length() - 1 && str[i] == str[i + 1]) {
            dp[i][i + 1] = true;
            ans = 2;
        }
    }

    for (int i = 3; i <= str.length(); i++) {
        for (int j = 0; j + i - 1 < str.length(); j++) {
            int k = j + i - 1;
            if (str[j] == str[k] && dp[j + 1][k - 1]) {
                dp[j][k] = dp[j + 1][k - 1];
                ans = i;
            }
        }
    }

    cout << ans;
    return 0;
}