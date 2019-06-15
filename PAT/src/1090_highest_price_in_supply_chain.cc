#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int N;
double P;
double rate;
int count_node = 0;
int max_depth = 0;

struct Node {
    vector<int> children;
} tree[100001];

void Dfs(int root, int depth) {
    if (tree[root].children.size() == 0) {
        if (depth > max_depth) {
            max_depth = depth;
            count_node = 1;
        } else if (depth == max_depth) {
            count_node++;
        }

        return;
    }

    for (int i = 0; i < tree[root].children.size(); i++) {
        Dfs(tree[root].children[i], depth + 1);
    }
}

int main() {
    cin >> N >> P >> rate;
    rate = 1.0 + rate / 100;
    int root;

    for (int i = 0; i < N; i++) {
        int tmp;
        cin >> tmp;

        if (tmp != -1) {
            tree[tmp].children.push_back(i);
        } else {
            root = i;
        }
    }

    Dfs(root, 0);
    printf("%.2f %d", P * pow(rate, max_depth), count_node);
    return 0;
}