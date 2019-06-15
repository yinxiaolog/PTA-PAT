#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <queue>
#include <climits>

using namespace std;

static const int kSize = 500;

struct Node {
    int from;
    int to;
    int weight_edge;
    int weight_vertex;
};

int N;
vector<Node> adj[kSize];
bool marked[kSize] = {false};
int weights[kSize] = {0};
int dis[kSize];
int w[kSize];
int num[kSize] = {0};

void Dijkstra(int s) {
    fill(dis, dis + kSize, INT_MAX);
    fill(num, num + kSize, 0);
    fill(w, w + kSize, 0);

    dis[s] = 0;
    w[s] = weights[s];
    num[s] = 1;

    priority_queue<pair<int, int>, vector<pair<int, int> >, greater<pair<int, int> > > pq;
    pq.push(make_pair(dis[s], s));

    while (!pq.empty()) {
        pair<int, int> p = pq.top();
        pq.pop();
        int u = p.second;
        if (marked[u]) {
            continue;
        }
        marked[u] = true;

        for (Node node : adj[u]) {
            int v = node.to;
            if (!marked[v]) {
                if (dis[u] + node.weight_edge < dis[v]) {
                    dis[v] = dis[u] + node.weight_edge;
                    w[v] = w[u] + weights[v];
                    num[v] = num[u];
                } else if (dis[u] + node.weight_edge == dis[v]) {
                    if (w[u] + weights[v] > w[v]) {
                        w[v] = w[u] + weights[v];
                    }

                    num[v] += num[u];
                }

                pq.push(make_pair(dis[v], v));
            }
        }
    }
}

int main() {
    int M, C1, C2;
    cin >> N >> M >> C1 >> C2;

    for (int i = 0; i < N; i++) {
        int w;
        cin >> w;
        weights[i] = w;
    }
    for (int i = 0; i < M; i++) {
        int from, to, w;
        cin >> from >> to >> w;
        Node node_from;
        node_from.from = from;
        node_from.to = to;
        node_from.weight_edge = w;
        node_from.weight_vertex = weights[from];
        adj[from].push_back(node_from);

        node_from.to = from;
        node_from.from = to;
        adj[to].push_back(node_from);
    }

    Dijkstra(C1);
    cout << num[C2] << " " << w[C2];
    return 0;
}