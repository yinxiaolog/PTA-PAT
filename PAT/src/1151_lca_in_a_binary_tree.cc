#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
    int val;
    Node *left = nullptr;
    Node *right = nullptr;

    Node(int _val) {
        val = _val;
    }
};

vector<int> in_order, pre_order;

int FindRootInOrder(int root, int left, int right) {
    for (int i = left; i <= right; ++i) {
        if (in_order[i] == root) {
            return i;
        }
    }

    return -1;
}

Node *Build(int in_left, int in_right, int pre_left, int pre_right) {
    if (in_left > in_right || pre_left > pre_right) {
        return nullptr;
    }

    int root = pre_order[pre_left];
    int root_index = FindRootInOrder(root, in_left, in_right);
    Node *node = new Node(root);

    int cnt = root_index - in_left;
    node->left = Build(in_left, root_index - 1, pre_left + 1, pre_left + cnt);
    node->right = Build(root_index + 1, in_right, pre_left + cnt + 1, pre_right);

    return node;
}

Node *Lca(Node *root, int u, int v) {
    if (root == nullptr) {
        return nullptr;
    }

    if (root->val == u || root->val == v) {
        return root;
    }

    Node *left = Lca(root->left, u, v);
    Node *right = Lca(root->right, u, v);

    if (left != nullptr && right != nullptr) {
        return root;
    }
    if (left != nullptr) {
        return left;
    }
    if (right != nullptr) {
        return right;
    }

    return nullptr;
}

int main() {
    int M, N;
    cin >> M >> N;

    for (int i = 0; i < N; ++i) {
        int val;
        cin >> val;
        in_order.emplace_back(val);
    }
    for (int i = 0; i < N; ++i) {
        int val;
        cin >> val;
        pre_order.emplace_back(val);
    }

    Node *root = Build(0, N - 1, 0, N - 1);

    for (int i = 0; i < M; ++i) {
        int u, v;
        cin >> u >> v;
        auto uPos = find(in_order.begin(), in_order.end(), u);
        auto vPos = find(in_order.begin(), in_order.end(), v);

        if (uPos == in_order.end() && vPos == in_order.end()) {
            printf("ERROR: %d and %d are not found.\n", u, v);
            continue;
        }
        if (uPos == in_order.end()) {
            printf("ERROR: %d is not found.\n", u);
            continue;
        }
        if (vPos == in_order.end()) {
            printf("ERROR: %d is not found.\n", v);
            continue;
        }

        Node *lca = Lca(root, u, v);

        if (lca->val != u && lca->val != v) {
            printf("LCA of %d and %d is %d.\n", u, v, lca->val);
            continue;
        }
        if (lca->val == u) {
            printf("%d is an ancestor of %d.\n", u, v);
            continue;
        }
        if (lca->val == v) {
            printf("%d is an ancestor of %d.\n", v, u);
        }
    }

    return 0;
}