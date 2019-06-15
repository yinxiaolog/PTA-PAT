#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

const int kSize = 10001;

int tree[kSize];
int N;
stack<int> nums;

void Build(int root) {
    if (root > N - 1) {
        return;
    }

    Build(root * 2 + 1);
    tree[root] = nums.top();
    nums.pop();
    Build(root * 2 + 2);
}

int main() {
    cin >> N;
    vector<int> sequence;
    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        sequence.push_back(num);
    }
    sort(sequence.begin(), sequence.end(), greater<int>());
    for (int i : sequence) {
        nums.push(i);
    }

    Build(0);
    for (int i = 0; i < N; i++) {
        if (i != N - 1) {
            cout << tree[i] << " ";
        } else {
            cout << tree[i];
        }
    }
    return 0;
}