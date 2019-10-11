#include <iostream>
#include <vector>
#include <string>

using namespace std;

const int kSize = 21;

struct Node {
    string val;
    int left;
    int right;

    Node() {}

    Node(string _val, int _left, int _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

Node tree[kSize];

string Dfs(int root) {
    if (root == -1) {
        return "";
    }

    if (tree[root].left == -1 && tree[root].right == -1) {
        return tree[root].val;
    }

    return '(' + Dfs(tree[root].left) + tree[root].val + Dfs(tree[root].right) + ')';
}

int main() {
    int N;
    cin >> N;

    bool marked[kSize] = {false};
    for (int i = 1; i <= N; ++i) {
        string val;
        int left, right;
        cin >> val >> left >> right;
        tree[i] = Node(val, left, right);
        if (left != -1) {
            marked[left] = true;
        }
        if (right != -1) {
            marked[right] = right;
        }
    }

    int root = -1;
    for (int i = 1; i <= N; ++i) {
        if (!marked[i]) {
            root = i;
            break;
        }
    }

    string ans = Dfs(root);
    if (ans.length() == 1) {
        cout << ans;
        return 0;
    }
    cout << ans.substr(1, ans.length() - 2);
    return 0;
}