#include <iostream>
#include <cmath>

using namespace std;

const int kSize = 100000;

int main() {
    int K;
    cin >> K;

    int nums[kSize];
    int dp[kSize];

    for (int i = 0; i < K; i++) {
        int tmp;
        cin >> tmp;
        nums[i] = tmp;
    }

    bool flag = false;
    for (int i = 0; i < K; i++) {
        if (nums[i] >= 0) {
            flag = true;
            break;
        }
    }

    if (!flag) {
        cout << 0 << " " << nums[0] << " " << nums[K - 1];
        return 0;
    }

    dp[0] = nums[0];
    for (int i = 1; i < K; i++) {
        dp[i] = max(dp[i - 1] + nums[i], nums[i]);
    }

    int start = -1, end = -1, sum = -1;
    for (int i = 0; i < K; i++) {
        if (dp[i] > sum && dp[i] >= 0) {
            sum = dp[i];
            end = i;
            start = i;
            for (int j = i; dp[j] > 0; j--) {
                start = j;
            }
        }
    }
    if (sum < 0) {
        cout << 0 << " " << nums[0] << " " << nums[K - 1];
    } else {
        cout << sum << " " << nums[start] << " " << nums[end];
    }
    return 0;
}