#include <iostream>
#include <unordered_set>

using namespace std;

const int kSize = 1 << 24;

int arr[kSize] = {0};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int M, N;
    cin >> M >> N;
    unordered_set<int> hash_set;
    int half = M * N / 2;
    int ans = 0;
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            int x;
            cin >> x;
            arr[x]++;
            if (arr[x] >= half) {
                ans = x;
            }
        }
    }

    cout << ans;
    return 0;
}