#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>
#include <queue>

using namespace std;

const int kSize = 501;

struct Node {
    int from;
    int to;
    int weight;

    Node(int _from, int _to, int _weight) {
        from = _from;
        to = _to;
        weight = _weight;
    }
};

int C, N, S, M;
int weights[kSize];
int dis[kSize];
bool marked[kSize] = {false};
vector<Node> adj[kSize];
vector<int> edge_to[kSize];
vector<int> path, tmp_path;

void Dijkstra(int s) {
    fill(dis, dis + kSize, INT_MAX);
    fill(marked, marked + kSize, false);
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

int min_send = INT_MAX, min_back = INT_MAX;

void Dfs(int v) {
    tmp_path.push_back(v);
    if (v == 0) {
        tmp_path.pop_back();
        int min_send_tmp = 0, min_back_tmp = 0;
        for (int i = tmp_path.size() - 1; i >= 0; i--) {
            int is_perfect = weights[tmp_path[i]] - C / 2;
            if (is_perfect < 0) {
                if (min_back_tmp - abs(is_perfect) < 0) {
                    min_send_tmp += abs(is_perfect) - min_back_tmp;
                    min_back_tmp = 0;
                } else {
                    min_back_tmp = min_back_tmp - abs(is_perfect);
                }
            } else {
                min_back_tmp += is_perfect;
            }
        }
        tmp_path.push_back(v);
        if (min_send_tmp < min_send) {
            min_send = min_send_tmp;
            min_back = min_back_tmp;
            path = tmp_path;
        } else if (min_send_tmp == min_send) {
            if (min_back_tmp < min_back) {
                min_back = min_back_tmp;
                path = tmp_path;
            }
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
    cin >> C >> N >> S >> M;
    for (int i = 1; i <= N; i++) {
        int w;
        cin >> w;
        weights[i] = w;
    }
    for (int i = 0; i < M; i++) {
        int from, to, weight;
        cin >> from >> to >> weight;
        adj[from].push_back(Node(from, to, weight));
        adj[to].push_back(Node(to, from, weight));
    }
    Dijkstra(0);
    Dfs(S);

    if (min_send == INT_MAX) {
        min_send = 0;
    }
    cout << min_send << " ";
    for (int i = path.size() - 1; i >= 0; i--) {
        cout << path[i];
        if (i != 0) {
            cout << "->";
        }
    }
    if (min_back == INT_MAX) {
        min_back = 0;
    }

    cout << " " << min_back;
    return 0;
}