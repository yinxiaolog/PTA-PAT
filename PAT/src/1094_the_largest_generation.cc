#include <iostream>
#include <vector>

using namespace std;

struct Node {
    int level;
    vector<int> children;
} tree[101];

int ans[101];
int N;

void Dfs(int root, int level) {
    ans[level]++;
    if (tree[root].children.empty()) {
        return;
    }

    for (int i = 0; i < tree[root].children.size(); i++) {
        Dfs(tree[root].children[i], level + 1);
    }
}

int main() {
    int M;
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

    int max = -1;
    int count = -1;

    for (int i = 1; i <= N; i++) {
        if (ans[i] > max) {
            max = ans[i];
            count = i;
        }
    }
    cout << max << " " << count;
    return 0;
}