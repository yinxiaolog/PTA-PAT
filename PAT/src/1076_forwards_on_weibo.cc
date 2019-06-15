#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int kSize = 1001;

struct Node {
    int from;
    int to;
    int level;
};
vector<Node> adj[kSize];
bool marked[kSize] = {false};
int N, L;

int Bfs(int v) {
    int ans = 0;
    queue<Node> q;
    Node s;
    s.to = v;
    s.level = 0;
    q.push(s);
    marked[v] = true;

    while (!q.empty()) {
        Node now = q.front();
        if (now.level <= L) {
            ans++;
        } else {
            break;
        }
        q.pop();
        for (Node node : adj[now.to]) {
            if (!marked[node.to]) {
                node.level = now.level + 1;
                q.push(node);
                marked[node.to] = true;
            }
        }
    }

    return ans - 1;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> L;
    for (int i = 1; i <= N; i++) {
        int M;
        cin >> M;

        for (int j = 0; j < M; j++) {
            int v;
            cin >> v;
            Node node;
            node.from = v;
            node.to = i;
            adj[v].push_back(node);
        }
    }

    int K;
    cin >> K;
    for (int i = 0; i < K; i++) {
        int v;
        cin >> v;
        fill(marked, marked + kSize, false);
        cout << Bfs(v) << endl;
    }
    return 0;
}