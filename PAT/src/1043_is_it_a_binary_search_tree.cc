#include <iostream>
#include <vector>

using namespace std;

struct Node {
    int key;
    Node *left;
    Node *right;
};

vector<int> pre_order;
vector<int> mirror_pre_order;
vector<int> post_order;
vector<int> mirror_post_order;

void insert(Node *&root, int data) {
    if (root == nullptr) {
        Node *node = new Node;
        node->key = data;
        node->left = node->right = nullptr;
        root = node;
        return;
    }

    if (data < root->key) {
        insert(root->left, data);
    } else {
        insert(root->right, data);
    }
}

void PreOrder(Node *root) {
    if (root == nullptr) {
        return;
    }

    pre_order.push_back(root->key);
    PreOrder(root->left);
    PreOrder(root->right);
}

void MirrorPreOrder(Node *root) {
    if (root == nullptr) {
        return;
    }

    mirror_pre_order.push_back(root->key);
    MirrorPreOrder(root->right);
    MirrorPreOrder(root->left);
}

void PostOrder(Node *root) {
    if (root == nullptr) {
        return;
    }

    PostOrder(root->left);
    PostOrder(root->right);
    post_order.push_back(root->key);
}

void MirrorPostOrder(Node *root) {
    if (root == nullptr) {
        return;
    }

    MirrorPostOrder(root->right);
    MirrorPostOrder(root->left);
    mirror_post_order.push_back(root->key);
}

int main() {
    int N;
    cin >> N;
    vector<int> sequence;
    Node *root = nullptr;
    for (int i = 0; i < N; i++) {
        int data;
        cin >> data;
        sequence.push_back(data);
        insert(root, data);
    }

    PreOrder(root);
    if (sequence == pre_order) {
        PostOrder(root);
        cout << "YES" << endl;
        for (int i = 0; i < post_order.size(); i++) {
            if (i != post_order.size() - 1) {
                cout << post_order[i] << " ";
            } else {
                cout << post_order[i];
            }
        }
    } else {
        MirrorPreOrder(root);
        if (sequence == mirror_pre_order) {
            cout << "YES" << endl;
            MirrorPostOrder(root);
            for (int i = 0; i < mirror_post_order.size(); i++) {
                if (i != mirror_post_order.size() - 1) {
                    cout << mirror_post_order[i] << " ";
                } else {
                    cout << mirror_post_order[i];
                }
            }
        } else {
            cout << "NO";
        }
    }

    return 0;
}