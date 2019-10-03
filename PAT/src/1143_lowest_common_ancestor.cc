#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
    Node *left = nullptr;
    Node *right = nullptr;
    int val;

    Node(int _val) {
        val = _val;
    }
};

vector<int> pre_order, in_order;

int FindRootIn(int left, int right, int key) {
    for (int i = left; i <= right; ++i) {
        if (in_order[i] == key) {
            return i;
        }
    }

    return -1;
}

Node *Build(int in_left, int in_right, int pre_left, int pre_right) {
    if (in_left > in_right) {
        return nullptr;
    }

    int root_index = FindRootIn(in_left, in_right, pre_order[pre_left]);
    int num = root_index - in_left;

    Node *node = new Node(pre_order[pre_left]);
    node->left = Build(in_left, root_index - 1, pre_left + 1, pre_left + num);
    node->right = Build(root_index + 1, in_right, pre_left + num + 1, pre_right);
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
        pre_order.emplace_back(val);
    }
    in_order = pre_order;
    sort(in_order.begin(), in_order.end());

    Node *root = Build(0, N - 1, 0, N - 1);

    for (int i = 0; i < M; ++i) {
        int u, v;
        cin >> u >> v;
        auto it_u = find(in_order.begin(), in_order.end(), u);
        auto it_v = find(in_order.begin(), in_order.end(), v);

        if (it_u == in_order.end() && it_v == in_order.end()) {
            printf("ERROR: %d and %d are not found.\n", u, v);
            continue;
        }
        if (it_u == in_order.end()) {
            printf("ERROR: %d is not found.\n", u);
            continue;
        }
        if (it_v == in_order.end()) {
            printf("ERROR: %d is not found.\n", v);
            continue;
        }

        Node *node = Lca(root, u, v);
        if (node->val != u && node->val != v) {
            printf("LCA of %d and %d is %d.\n", u, v, node->val);
            continue;
        }
        if (node->val == u) {
            printf("%d is an ancestor of %d.\n", u, v);
            continue;
        }
        if (node->val == v) {
            printf("%d is an ancestor of %d.\n", v, u);
            continue;
        }
    }

    return 0;
}