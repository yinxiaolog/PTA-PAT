#include <iostream>
#include <vector>

using namespace std;

struct Node {
    vector<int> children;
} tree[101];

int ans[101] = {0};
int max_depth = -1;

void Dfs(int root, int depth) {
    if (tree[root].children.empty()) {
        max_depth = max(depth, max_depth);
        ans[depth]++;
        return;
    }

    for (int i = 0; i < tree[root].children.size(); i++) {
        Dfs(tree[root].children[i], depth + 1);
    }
}

int main() {
    int N, M;
    cin >> N >> M;

    for (int i = 0; i < M; i++) {
        int root;
        cin >> root;
        int K;
        cin >> K;
        for (int j = 0; j < K; j++) {
            int child;
            cin >> child;
            tree[root].children.push_back(child);
        }
    }

    Dfs(1, 1);
    for (int i = 1; i <= max_depth; i++) {
        if (i != max_depth) {
            cout << ans[i] << " ";
        } else {
            cout << ans[i];
        }
    }
    return 0;
}