#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

struct Node {
    int key;
    int left;
    int right;
};

const int MAX = 10;
int n;
Node tree[MAX];
vector<int> level_order;
vector<int> in_order;

void Bfs(int root) {
    queue<int> q;
    q.push(root);

    while (!q.empty()) {
        int tmp = q.front();
        q.pop();
        root = tmp;
        level_order.push_back(tmp);
        if (tree[root].left != -1) {
            q.push(tree[root].left);
        }

        if (tree[root].right != -1) {
            q.push(tree[root].right);
        }
    }
}

void InOrder(int root) {
    if (root == -1) {
        return;
    }

    InOrder(tree[root].left);
    in_order.push_back(tree[root].key);
    InOrder(tree[root].right);
}

int main() {
    cin >> n;
    bool is_root[MAX];
    fill(is_root, is_root + MAX, true);
    for (int i = 0; i < n; i++) {
        string tmp;
        int left;
        int right;

        cin >> tmp;
        left = tmp == "-" ? -1 : stoi(tmp);
        cin >> tmp;
        right = tmp == "-" ? -1 : stoi(tmp);

        Node node;
        node.key = i;
        node.left = right;
        node.right = left;
        tree[i] = node;

        if (left != -1) {
            is_root[left] = false;
        }

        if (right != -1) {
            is_root[right] = false;
        }
    }

    int root = -1;
    for (int i = 0; i < n; i++) {
        if (is_root[i] == true) {
            root = i;
            break;
        }
    }

    Bfs(root);
    InOrder(root);

    for (int i = 0; i < level_order.size(); i++) {
        if (i != level_order.size() - 1) {
            cout << level_order[i] << ' ';
        } else {
            cout << level_order[i];
        }
    }
    cout << endl;
    for (int i = 0; i < in_order.size(); i++) {
        if (i != in_order.size() - 1) {
            cout << in_order[i] << ' ';
        } else {
            cout << in_order[i];
        }
    }

    return 0;
}