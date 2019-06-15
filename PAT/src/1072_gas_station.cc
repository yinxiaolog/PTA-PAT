#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <algorithm>
#include <climits>
#include <cmath>

using namespace std;

const int kSize = 1011;

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
bool marked[kSize];
int N, M, K, Ds;
int ans_index = INT_MAX;
int min_dis = -1;
double min_avg_dis = INT_MAX;

void Dijkstra(int s) {
    fill(dis, dis + kSize, INT_MAX);
    fill(marked, marked + kSize, false);
    for (int i = 0; i < kSize; i++) {
        edge_to[i].clear();
    }

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
            }
            pq.push(make_pair(dis[v], v));
        }
    }
}

int String2Int(string str) {
    int ans;
    if (str[0] == 'G') {
        str = str.substr(1);
        ans = stoi(str) + N;
    } else {
        ans = stoi(str);
    }

    return ans;
}

int get_max_dis() {
    int ans = -1;
    for (int i = 1; i <= N; i++) {
        if (dis[i] > ans) {
            ans = dis[i];
        }
    }

    return ans;
}

int get_min_dis() {
    int ans = INT_MAX;
    for (int i = 1; i <= N; i++) {
        if (dis[i] < ans) {
            ans = dis[i];
        }
    }

    return ans;
}

double get_min_avg_dis() {
    double ans = 0;

    for (int i = 1; i <= N; i++) {
        ans += dis[i];
    }
    return ans / N;
}

void solve(int v) {
    Dijkstra(v);
    int max_dis_tmp = get_max_dis();
    int min_dis_tmp = get_min_dis();
    double min_avg_tmp = get_min_avg_dis();

    if (max_dis_tmp <= Ds) {
        if (min_dis_tmp > min_dis) {
            min_dis = min_dis_tmp;
            min_avg_dis = min_avg_tmp;
            ans_index = v;
        } else if (min_dis_tmp == min_dis) {
            //min_avg_dis = min_avg_tmp;
            if (min_avg_tmp < min_avg_dis) {
                min_avg_dis = min_avg_tmp;
                ans_index = v;
            } else if (min_avg_tmp == min_avg_dis) {
                if (v < ans_index) {
                    ans_index = v;
                }
            }
        }
    }
}

int main() {
    cin >> N >> M >> K >> Ds;
    for (int i = 0; i < K; i++) {
        string from_str, to_str;
        int weight;
        cin >> from_str >> to_str >> weight;
        int from = String2Int(from_str);
        int to = String2Int(to_str);
        adj[from].push_back(Node(from, to, weight));
        adj[to].push_back(Node(to, from, weight));
    }

    Dijkstra(N + 1);

    for (int i = N + 1; i <= N + M; i++) {
        solve(i);
    }

    if (ans_index != INT_MAX) {
        cout << 'G' << ans_index - N << endl;
        printf("%.1f %.1f", (double) min_dis, min_avg_dis);
    } else {
        cout << "No Solution";
    }
    return 0;
}