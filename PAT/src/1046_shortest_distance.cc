#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int main() {
    int N;
    cin >> N;
    vector<int> nums;
    for (int i = 0; i < N; i++) {
        int x;
        cin >> x;
        nums.push_back(x);
    }

    vector<int> sum;
    for (int i = 0; i < N; i++) {
        if (i == 0) {
            sum.push_back(nums[0]);
        } else {
            //sum[i] = sum[i - 1] + nums[i];
            sum.push_back(sum[i - 1] + nums[i]);
        }
    }

    int M;
    cin >> M;
    for (int i = 0; i < M; i++) {
        int from, to;
        cin >> from >> to;
        from--;
        to--;
        int dis;
        if (from < to) {
            dis = sum[to] - nums[to];
            if (from > 0) {
                dis -= sum[from - 1];
            }
        } else {
            dis = sum[from] - nums[from];
            if (to > 0) {
                dis -= sum[to - 1];
            }
        }

        cout << min(dis, sum[N - 1] - dis) << endl;
    }
    return 0;
}