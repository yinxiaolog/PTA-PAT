#include <iostream>
#include <vector>
#include <stack>
#include <string>

using namespace std;

struct Node {
    int key;
    Node *left;
    Node *right;
};

vector<int> in_order;
vector<int> pre_order;
vector<int> post_order;

Node *Build(int pre_left, int pre_right, int in_left, int in_right) {
    if (pre_left > pre_right) {
        return nullptr;
    }

    Node *root = new Node;
    root->key = pre_order[pre_left];
    int index = -1;
    for (int i = in_left; i <= in_right; i++) {
        if (in_order[i] == root->key) {
            index = i;
            break;
        }
    }

    int count = index - in_left;

    root->left = Build(pre_left + 1, pre_left + count, in_left, index - 1);
    root->right = Build(pre_left + count + 1, pre_right, index + 1, in_right);
    return root;
}

void PostOrder(Node *root) {
    if (root == nullptr) {
        return;
    }
    PostOrder(root->left);
    PostOrder(root->right);
    post_order.push_back(root->key);
}

int main() {
    int n;
    cin >> n;
    stack<int> operations;
    for (int i = 0; i < 2 * n; i++) {
        string operation;
        cin >> operation;
        if (operation == "Push") {
            int tmp;
            cin >> tmp;
            operations.push(tmp);
            pre_order.push_back(tmp);
        }

        if (operation == "Pop") {
            int tmp = operations.top();
            operations.pop();
            in_order.push_back(tmp);
        }
    }

    Node *root = Build(0, n - 1, 0, n - 1);
    PostOrder(root);
    for (int i = 0; i < post_order.size() - 1; i++) {
        cout << post_order[i] << ' ';
    }
    cout << post_order[post_order.size() - 1];

    return 0;
}