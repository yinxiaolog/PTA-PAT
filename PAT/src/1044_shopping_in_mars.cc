#include <iostream>
#include <vector>

using namespace std;

const int kSize = 100001;

int sum[kSize];
int N, M;

int BinarySearch(int i) {
    int left = i, right = N;

    while (left < right) {
        int mid = (left + right) / 2;
        if (sum[mid] - sum[i - 1] >= M) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    return right;
}

int main() {
    cin >> N >> M;
    sum[0] = 0;
    for (int i = 1; i <= N; i++) {
        cin >> sum[i];
        sum[i] += sum[i - 1];
    }

    vector<pair<int, int> > ans;
    int min = sum[N];
    for (int i = 1; i <= N; i++) {
        int j = BinarySearch(i);
        if (sum[j] - sum[i - 1] > min) {
            continue;
        }

        if (M <= sum[j] - sum[i - 1]) {
            if (sum[j] - sum[i - 1] < min) {
                ans.clear();
                min = sum[j] - sum[i - 1];
            }

            ans.emplace_back(make_pair(i, j));
        }
    }

    for (auto p : ans) {
        cout << p.first << '-' << p.second << endl;
    }
}