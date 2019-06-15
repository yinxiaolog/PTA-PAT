#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int kSize = 101;

struct Node {
    int id;
    int weight;
    vector<Node> children;
} tree[kSize];

bool cmp(Node &node_a, Node &node_b) {
    return node_a.weight > node_b.weight;
}

int S;
vector<int> tmp_path;
vector<vector<int> > ans;

void Dfs(int root, int weight) {
    tmp_path.push_back(tree[root].weight);
    if (tree[root].children.empty()) {
        if (weight == tree[root].weight) {
            ans.push_back(tmp_path);
        }
        //tmp_path.pop_back();
        return;
    }

    for (int i = 0; i < tree[root].children.size(); i++) {
        Dfs(tree[root].children[i].id, weight - tree[root].weight);
        tmp_path.pop_back();
    }
}

int main() {
    int N, M;
    cin >> N >> M >> S;

    int weights[kSize] = {0};
    for (int i = 0; i < N; i++) {
        int w;
        cin >> w;
        weights[i] = w;
    }

    for (int i = 0; i < M; i++) {
        int root;
        cin >> root;
        tree[root].id = root;
        tree[root].weight = weights[root];
        int K;
        cin >> K;
        for (int j = 0; j < K; j++) {
            int child;
            cin >> child;

            Node node;
            node.id = child;
            node.weight = weights[child];
            tree[root].children.push_back(node);
        }
        sort(tree[root].children.begin(), tree[root].children.end(), cmp);
    }

    for (int i = 0; i < N; i++) {
        tree[i].weight = weights[i];
    }
    Dfs(0, S);
    for (int i = 0; i < ans.size(); i++) {
        for (int j = 0; j < ans[i].size(); j++) {
            if (j != ans[i].size() - 1) {
                cout << ans[i][j] << " ";
            } else {
                cout << ans[i][j];
            }
        }

        if (i != ans.size() - 1) {
            cout << endl;
        }
    }
    return 0;
}