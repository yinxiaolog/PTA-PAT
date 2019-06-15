#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <queue>
#include <unordered_map>
#include <climits>

using namespace std;

const int kSize = 201;

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

vector<Node> adj[kSize];
vector<int> edge_to[kSize];
int dis[kSize];
int weights[kSize] = {};
bool marked[kSize];
vector<int> path, tmp_path;

int start;

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
                    edge_to[v].clear();
                    dis[v] = dis[u] + node.weight;
                    edge_to[v].push_back(u);
                } else if (dis[u] + node.weight == dis[v]) {
                    edge_to[v].push_back(u);
                }
            }

            pq.push(make_pair(dis[v], v));
        }
    }
}

int routes = 0, happiness = -1;
double avg_happiness = -1;

void Dfs(int s) {
    tmp_path.push_back(s);

    if (s == start) {
        routes++;
        int tmp_happiness = 0;
        for (int i : tmp_path) {
            tmp_happiness += weights[i];
        }
        double tmp_avg = (double) tmp_happiness / (tmp_path.size() - 1);

        if (tmp_happiness > happiness) {
            happiness = tmp_happiness;
            avg_happiness = tmp_avg;
            path = tmp_path;
        } else if (tmp_happiness == happiness) {
            if (tmp_avg > avg_happiness) {
                avg_happiness = tmp_avg;
                path = tmp_path;
            }
        }
        tmp_path.pop_back();
        return;
    }

    for (int i : edge_to[s]) {
        Dfs(i);
    }

    tmp_path.pop_back();
}

int main() {
    int N, K;
    string s;
    cin >> N >> K >> s;

    unordered_map<string, int> s2int;
    unordered_map<int, string> int2s;
    s2int.insert(pair<string, int>(s, N));
    int2s.insert(pair<int, string>(N, s));

    for (int i = 1; i < N; i++) {
        string city;
        int weight;
        cin >> city >> weight;
        s2int.insert(pair<string, int>(city, i));
        int2s.insert(pair<int, string>(i, city));
        weights[i] = weight;
    }

    for (int i = 0; i < K; i++) {
        string from_city, to_city;
        int weight;
        cin >> from_city >> to_city >> weight;
        int from = s2int[from_city];
        int to = s2int[to_city];
        adj[from].push_back(Node(from, to, weight));
        adj[to].push_back(Node(to, from, weight));
    }
    start = s2int[s];
    Dijkstra(s2int[s]);
    Dfs(s2int["ROM"]);
    cout << routes << " " << dis[s2int["ROM"]] << " " << happiness << " " << (int) avg_happiness << endl;
    for (int i = path.size() - 1; i >= 0; i--) {
        if (i != 0) {
            cout << int2s[path[i]] << "->";
        } else {
            cout << int2s[path[i]];
        }
    }
    return 0;
}