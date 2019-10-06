#include <iostream>
#include <vector>

using namespace std;

struct Node {
    Node *left = nullptr;
    Node *right = nullptr;
    int val;

    Node(int _val) {
        val = _val;
    }
};

vector<int> pre_order, in_order, post_order;

int FindRootInOrder(int left, int right, int key) {
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

    int root_index = FindRootInOrder(in_left, in_right, pre_order[pre_left]);
    int num = root_index - in_left;

    Node *node = new Node(pre_order[pre_left]);
    node->left = Build(in_left, root_index - 1, pre_left + 1, pre_left + num);
    node->right = Build(root_index + 1, in_right, pre_left + num + 1, pre_right);
    return node;
}

void PostOrder(Node *root) {
    if (nullptr == root) {
        return;
    }

    PostOrder(root->left);
    PostOrder(root->right);
    post_order.emplace_back(root->val);
}

int main() {
    int N;
    cin >> N;

    for (int i = 0; i < N; ++i) {
        int val;
        cin >> val;
        pre_order.emplace_back(val);
    }
    for (int i = 0; i < N; ++i) {
        int val;
        cin >> val;
        in_order.emplace_back(val);
    }

    Node *root = Build(0, N - 1, 0, N - 1);
    PostOrder(root);
    cout << post_order[0];
}