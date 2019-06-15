#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

const int kSize = 1000;

vector<int> adj[kSize];
bool marked[kSize];
int N;
int s;

void Dfs(int v) {
    if (v == s) {
        return;
    }
    marked[v] = true;

    for (int i : adj[v]) {
        if (!marked[i]) {
            Dfs(i);
        }
    }
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    int M, K;
    cin >> N >> M >> K;

    for (int i = 0; i < M; i++) {
        int from;
        int to;
        cin >> from >> to;
        adj[from].push_back(to);
        adj[to].push_back(from);
    }

    for (int i = 0; i < K; i++) {
        fill(marked, marked + kSize, false);

        cin >> s;
        int count = 0;
        for (int j = 1; j <= N; j++) {
            if (!marked[j] && j != s) {
                Dfs(j);
                count++;
            }
        }

        cout << count - 1 << endl;
    }
    return 0;
}