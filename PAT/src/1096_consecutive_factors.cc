#include <iostream>
#include <vector>
#include <cmath>
#include <climits>

using namespace std;

const int kSize = 1 << 16;

bool factors[kSize] = {false};

int main() {
    int N;
    cin >> N;
    int sqrt_N = (int) sqrt(N) + 1;
    for (int i = 2; i <= sqrt_N; i++) {
        if (N % i == 0) {
            factors[i] = true;
        }
    }

    vector<int> ans;
    for (int i = 0; i <= sqrt_N; i++) {
        vector<int> tmp;

        int j = i;
        if (factors[j]) {
            long long product = 1;
            while (factors[j]) {
                product *= j;
                if (N % product != 0) {
                    break;
                }
                tmp.push_back(j);
                j++;
            }

            if (tmp.size() > ans.size()) {
                ans = tmp;
            }
        }
    }

    if (ans.empty()) {
        cout << 1 << endl << N;
        return 0;
    }
    cout << ans.size() << endl;
    for (int i = 0; i < ans.size(); i++) {
        if (i == 0) {
            cout << ans[i];
        } else {
            cout << '*' << ans[i];
        }
    }
}