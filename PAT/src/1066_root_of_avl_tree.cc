#include <iostream>
#include <cmath>

using namespace std;

struct Node {
    int key;
    int height;
    Node *left;
    Node *right;
};

int Height(Node *root) {
    if (root == nullptr) {
        return 0;
    }

    return root->height;
}

void UpdateHeight(Node *root) {
    root->height = max(Height(root->left), Height(root->right)) + 1;
}

Node *LeftRotate(Node *root) {
    Node *root_right_left = root->right->left;
    Node *root_right = root->right;

    root_right->left = root;
    root->right = root_right_left;
    UpdateHeight(root);
    UpdateHeight(root_right);
    root = root_right;
    return root;
}

Node *RightRotate(Node *root) {
    Node *root_left_right = root->left->right;
    Node *root_left = root->left;

    root_left->right = root;
    root->left = root_left_right;
    UpdateHeight(root);
    UpdateHeight(root_left);
    root = root_left;
    return root;
}

void Insert(Node *&root, int key) {
    if (key == 49) {
        int j = 0;
    }
    if (root == nullptr) {
        Node *node = new Node;
        node->key = key;
        node->height = 1;
        node->left = node->right = nullptr;
        root = node;
        return;
    }

    if (key < root->key) {
        Insert(root->left, key);
        UpdateHeight(root);
        if (Height(root->left) - Height(root->right) == 2) {
            if (Height(root->left->left) - Height(root->left->right) == 1) {
                root = RightRotate(root);
            } else if (Height(root->left->left) - Height(root->left->right) == -1) {
                root->left = LeftRotate(root->left);
                root = RightRotate(root);
            }
        }
    } else {
        Insert(root->right, key);
        UpdateHeight(root);
        if (Height(root->left) - Height(root->right) == -2) {
            if (Height(root->right->left) - Height(root->right->right) == -1) {
                root = LeftRotate(root);
            } else if (Height(root->right->left) - Height(root->right->right) == 1) {
                root->right = RightRotate(root->right);
                root = LeftRotate(root);
            }
        }
    }
}

int main() {
    int N;
    cin >> N;
    Node *root = nullptr;
    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        Insert(root, num);
    }
    cout << root->key;
    return 0;
}