#include <iostream>
#include <algorithm>

using namespace std;

const int kSize = 100000;

int nums[kSize];

int Find(int len) {
    for (int i = 1; i < len; i++) {
        if (i != nums[i]) {
            return i;
        }
    }

    return -1;
}

int main() {
    int N;
    cin >> N;

    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        nums[num] = i;
    }

    int ans = 0;
    for (int i = 0; i < N; i++) {
        if (i != nums[i]) {
            while (nums[0] != 0) {
                swap(nums[0], nums[nums[0]]);
                ans++;
            }

            if (nums[i] != i) {
                swap(nums[0], nums[i]);
                ans++;
            }
        }
    }

    cout << ans;
    return 0;
}