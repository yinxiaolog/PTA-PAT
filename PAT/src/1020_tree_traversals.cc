#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Node {
    int key;
    Node *left;
    Node *right;
};

vector<int> post_order;
vector<int> in_order;
vector<int> layer_order;
int n;

int find(int key) {
    for (int i = 0; i < in_order.size(); i++) {
        if (in_order[i] == key) {
            return i;
        }
    }

    return -1;
}

Node *Build(int post_left, int post_right, int in_left, int in_right) {
    if (post_left > post_right) {
        return nullptr;
    }

    Node *root = new Node;
    root->key = post_order[post_right];

    int index = find(post_order[post_right]);
    int num = index - in_left;

    root->left = Build(post_left, post_left + num - 1, in_left, index - 1);
    root->right = Build(post_left + num, post_right - 1, index + 1, in_right);

    return root;
}

void Bfs(Node *root) {
    queue<Node *> q;
    q.push(root);

    while (!q.empty()) {
        Node *node = q.front();
        q.pop();
        layer_order.push_back(node->key);

        if (node->left != nullptr) {
            q.push(node->left);
        }

        if (node->right != nullptr) {
            q.push(node->right);
        }
    }
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        int tmp = 0;
        cin >> tmp;
        post_order.push_back(tmp);
    }

    for (int i = 0; i < n; i++) {
        int tmp = 0;
        cin >> tmp;
        in_order.push_back(tmp);
    }

    Node *root = Build(0, n - 1, 0, n - 1);
    Bfs(root);
    for (int i = 0; i < layer_order.size() - 1; i++) {
        cout << layer_order[i] << " ";
    }

    cout << layer_order[layer_order.size() - 1];
    return 0;
}