#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <queue>

using namespace std;

const int kSize = 501;

struct Node {
    int from;
    int to;
    int weight;
    int cost;

    Node(int _from, int _to, int _weight, int _cost) {
        from = _from;
        to = _to;
        weight = _weight;
        cost = _cost;
    }
};

int N, M, S, D;
vector<Node> adj[kSize];
bool marked[kSize] = {false};
vector<int> edge_to[kSize];
int dis[kSize];
vector<int> path, tmp_path;
int min_cost = INT_MAX;

void Dijkstra(int s) {
    fill(marked, marked + kSize, false);
    fill(dis, dis + kSize, INT_MAX);

    dis[s] = 0;
    edge_to[s].push_back(s);
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
                if (dis[u] + node.weight < dis[v]) {
                    dis[v] = dis[u] + node.weight;
                    edge_to[v].clear();
                    edge_to[v].push_back(u);
                } else if (dis[u] + node.weight == dis[v]) {
                    edge_to[v].push_back(u);
                }

                pq.push(make_pair(dis[v], v));
            }
        }
    }
}

int get_cost(int from, int to) {
    for (Node node : adj[from]) {
        if (node.to == to) {
            return node.cost;
        }
    }
    return INT_MIN;
}

void Dfs(int v) {
    tmp_path.push_back(v);
    if (v == S) {
        int tmp_cost = 0;
        for (int i = tmp_path.size() - 1; i > 0; i--) {
            tmp_cost += get_cost(tmp_path[i], tmp_path[i - 1]);
        }
        if (tmp_cost < min_cost) {
            min_cost = tmp_cost;
            path = tmp_path;
        }
        tmp_path.pop_back();
        return;
    }

    for (int i : edge_to[v]) {
        Dfs(i);
    }

    tmp_path.pop_back();
}

int main() {
    cin >> N >> M >> S >> D;
    for (int i = 0; i < M; i++) {
        int from, to, weight, cost;
        cin >> from >> to >> weight >> cost;
        adj[from].push_back(Node(from, to, weight, cost));
        adj[to].push_back(Node(to, from, weight, cost));
    }

    Dijkstra(S);
    Dfs(D);
    for (int i = path.size() - 1; i >= 0; i--) {
        cout << path[i] << " ";
    }
    cout << dis[D] << " ";
    cout << min_cost;
}