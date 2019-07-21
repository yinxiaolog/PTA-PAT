#include <iostream>
#include <algorithm>

using namespace std;

const int kSize = 100000;

long sequence[kSize];

int main() {
    int N, p;
    cin >> N >> p;
    for (int i = 0; i < N; i++) {
        cin >> sequence[i];
    }

    sort(sequence, sequence + N);

    int ans = 0;
    for (int i = 0; i < N; i++) {
        int j = upper_bound(sequence + i + 1, sequence + N, sequence[i] * p) - sequence;

        if (j - i > ans) {
            ans = j - i;
        }
    }

    cout << ans;
    return 0;
}