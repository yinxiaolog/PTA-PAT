#include <iostream>
#include <vector>

using namespace std;

const int kSize = 1001;

vector<int> adj[kSize];

bool IsTopologicalOrder(vector<int> degrees, vector<int> &list) {
    for (int i : list) {
        if (degrees[i] != 0) {
            return false;
        } else {
            for (int j : adj[i]) {
                degrees[j]--;
            }
        }
    }

    return true;
}

int main() {
    int N, M;
    cin >> N >> M;

    vector<int> degrees(N + 1, 0);
    for (int i = 0; i < M; ++i) {
        int from, to;
        cin >> from >> to;
        adj[from].push_back(to);
        degrees[to]++;
    }

    int K;
    cin >> K;
    vector<int> ans;
    for (int i = 0; i < K; ++i) {
        vector<int> list(N);
        for (int j = 0; j < N; ++j) {
            cin >> list[j];
        }

        if (!IsTopologicalOrder(degrees, list)) {
            ans.emplace_back(i);
        }
    }

    for (int i = 0; i < ans.size(); ++i) {
        if (i == 0) {
            cout << ans[i];
        } else {
            cout << ' ' << ans[i];
        }
    }
}