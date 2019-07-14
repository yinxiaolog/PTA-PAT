#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int kMaxN = 10001;
const int kMaxV = 101;

int w[kMaxN] = {0}, dp[kMaxN] = {0};
bool choice[kMaxN][kMaxV] = {false};

bool Cmp(int one, int another) {
    return one > another;
}

int main() {
    int N, M;
    cin >> N >> M;
    for (int i = 1; i <= N; i++) {
        cin >> w[i];
    }

    sort(w + 1, w + N + 1, Cmp);

    for (int i = 1; i <= N; i++) {
        for (int v = M; v >= w[i]; v--) {
            if (dp[v] <= dp[v - w[i]] + w[i]) {
                dp[v] = dp[v - w[i]] + w[i];
                choice[i][v] = true;
            } else {
                choice[i][v] = false;
            }
        }
    }

    vector<int> ans;

    if (dp[M] == M) {
        int k = N, v = M;
        while (k >= 0) {
            if (choice[k][v]) {
                ans.push_back(k);
                v -= w[k];
            }
            k--;
        }

        for (int i = 0; i < ans.size(); i++) {
            if (i != ans.size() - 1) {
                cout << w[ans[i]] << ' ';
            } else {
                cout << w[ans[i]];
            }
        }
    } else {
        cout << "No Solution";
    }

    return 0;
}