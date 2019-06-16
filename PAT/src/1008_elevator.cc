#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int main() {
    vector<int> nums;
    nums.push_back(0);
    int N;
    cin >> N;
    for (int i = 0; i < N; i++) {
        int tmp;
        cin >> tmp;
        nums.push_back(tmp);
    }

    int sum = 0;
    for (int i = 1; i < nums.size(); i++) {
        int x = nums[i] - nums[i - 1];
        if (x < 0) {
            sum += 4 * abs(x);
        } else {
            sum += 6 * x;
        }

        sum += 5;
    }
    cout << sum;
    return 0;
}