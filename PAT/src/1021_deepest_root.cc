#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

const int kSize = 10001;

vector<int> adj[kSize];
bool marked[kSize];
int N;
int max_depth = -1;
set<int> tmp;


void Dfs(int v, int depth) {
    marked[v] = true;
    if (depth > max_depth) {
        max_depth = depth;
        tmp.clear();
        tmp.insert(v);
    } else if (depth == max_depth) {
        tmp.insert(v);
    }
    for (int i : adj[v]) {
        if (!marked[i]) {
            Dfs(i, depth + 1);
        }
    }
}

int main() {
    cin >> N;
    fill(marked, marked + kSize, false);
    for (int i = 0; i < N - 1; i++) {
        int from, to;
        cin >> from >> to;
        adj[from].push_back(to);
        adj[to].push_back(from);
    }

    int count = 0;
    for (int i = 1; i <= N; i++) {
        if (!marked[i]) {
            Dfs(i, 0);
            count++;
        }
    }

    if (count > 1) {
        cout << "Error: " << count << " components";
        return 0;
    }

    set<int> ans = tmp;
    int s = *tmp.begin();
    max_depth = -1;
    tmp.clear();
    fill(marked, marked + kSize, false);
    Dfs(s, 0);
    for (int i : tmp) {
        ans.insert(i);
    }
    for (int i : ans) {
        cout << i << endl;
    }
    return 0;
}