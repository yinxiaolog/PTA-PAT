#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;

int BinarySearch(vector<int> &v, int i, int x) {
    int left = i, right = v.size() - 1;
    while (left <= right) {
        int mid = (left + right) / 2;
        if (v[mid] == x) {
            return mid;
        } else if (v[mid] < x) {
            left = mid + 1;
        } else if (v[mid] > x) {
            right = mid - 1;
        }
    }

    return -1;
}

int main() {
    cin >> N >> M;
    vector<int> nums;
    for (int i = 0; i < N; i++) {
        int x;
        cin >> x;
        nums.push_back(x);
    }

    sort(nums.begin(), nums.end());

    for (int i = 0; i < nums.size(); i++) {
        int j = BinarySearch(nums, i + 1, M - nums[i]);
        if (j != -1) {
            cout << nums[i] << " " << nums[j];
            return 0;
        }
    }

    cout << "No Solution";
    return 0;
}