#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

struct Node {
    int data;
    vector<int> children;
} tree[100001];

double ans = 0;
int N;
double P;
double rate;

void Dfs(int root, int depth) {
    if (tree[root].children.size() == 0) {
        ans += tree[root].data * P * pow(rate, depth);
        return;
    }

    for (int i = 0; i < tree[root].children.size(); i++) {
        Dfs(tree[root].children[i], depth + 1);
    }
}

int main() {
    cin >> N >> P >> rate;
    rate = 1.0 + rate / 100;

    for (int i = 0; i < N; i++) {
        int K;
        cin >> K;
        for (int j = 0; j < K; j++) {
            int child;
            cin >> child;
            tree[i].children.push_back(child);
        }

        if (K == 0) {
            int child;
            cin >> child;
            tree[i].data = child;
        }
    }
    Dfs(0, 0);
    printf("%.1f", ans);
    return 0;
}