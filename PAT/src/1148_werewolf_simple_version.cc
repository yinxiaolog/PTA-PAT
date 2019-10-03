#include <iostream>
#include <vector>

using namespace std;

const int kSize = 101;

int players[kSize];

int main() {
    int N;
    cin >> N;
    for (int i = 1; i <= N; ++i) {
        cin >> players[i];
    }

    for (int i = 1; i < N; ++i) {
        for (int j = i + 1; j <= N; ++j) {
            int marked[kSize] = {0};
            fill(marked, marked + kSize, 1);
            marked[i] = marked[j] = -1;
            vector<int> ans;
            for (int k = 1; k <= N; ++k) {
                if (players[k] * marked[abs(players[k])] < 0) {
                    ans.emplace_back(k);
                }
            }

            if (ans.size() == 2 && marked[ans[0]] + marked[ans[1]] == 0) {
                cout << i << ' ' << j << endl;
                return 0;
            }
        }
    }

    cout << "No Solution" << endl;
}