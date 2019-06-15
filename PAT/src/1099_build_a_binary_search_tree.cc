#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
#include <queue>

using namespace std;

const int kSize = 101;

struct Node {
    int key;
    int left;
    int right;
} tree[kSize];

int N;
stack<int> nums;
vector<int> ans;

void InOrder(int root) {
    if (root == -1) {
        return;
    }

    InOrder(tree[root].left);
    tree[root].key = nums.top();
    nums.pop();
    InOrder(tree[root].right);
}

void Bfs(int root) {
    queue<int> q;
    q.push(root);

    while (!q.empty()) {
        int key = q.front();
        q.pop();
        ans.push_back(tree[key].key);
        if (tree[key].left != -1) {
            q.push(tree[key].left);
        }

        if (tree[key].right != -1) {
            q.push(tree[key].right);
        }
    }
}

int main() {
    cin >> N;
    vector<int> sequence;

    for (int i = 0; i < N; i++) {
        int left, right;
        cin >> left >> right;
        tree[i].left = left;
        tree[i].right = right;
    }
    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        sequence.push_back(num);
    }

    sort(sequence.begin(), sequence.end(), greater<int>());
    for (int i : sequence) {
        nums.push(i);
    }

    InOrder(0);
    Bfs(0);
    for (int i = 0; i < ans.size(); i++) {
        if (i != ans.size() - 1) {
            cout << ans[i] << " ";
        } else {
            cout << ans[i];
        }
    }
    return 0;
}