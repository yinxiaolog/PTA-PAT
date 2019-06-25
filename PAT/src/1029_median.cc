#include <iostream>
#include <climits>

using namespace std;

const int kSize = 200000;

int S1[kSize];
int S2;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int N, M;

    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> S1[i];
    }
    S1[N] = INT_MAX;

    cin >> M;

    int mid = (N + M - 1) / 2;
    int i = 0, j = 0;
    int count = -1;
    for (; j < M; j++) {
        cin >> S2;
        while (S1[i] < S2) {
            count++;
            if (count == mid) {
                cout << S1[i];
            }
            i++;
        }

        count++;
        if (count == mid) {
            cout << S2;
        }
    }
    for (; i < N; i++) {
        count++;
        if (count == mid) {
            cout << S1[i];
            break;
        }
    }
    return 0;
}