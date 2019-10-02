#include <iostream>
#include <cstdio>
#include <vector>
#include <climits>
#include <unordered_set>

using namespace std;

const int kSize = 201;

struct Node {
    int from;
    int to;
    int dis;

    Node(int _from, int _to, int _dis) {
        from = _from;
        to = _to;
        dis = _dis;
    }
};

vector<Node> adj[kSize];
int N, M;

bool IsConnect(int from, int to) {
    for (Node node : adj[from]) {
        if (node.to == to) {
            return true;
        }
    }

    return false;
}

bool IsSimpleCycle(vector<int> &path) {
    if (path.size() != N + 1 || path[0] != path[N]) {
        return false;
    }

    unordered_set<int> s;

    for (int i = 0; i < path.size() - 1; ++i) {
        if (!IsConnect(path[i], path[i + 1])) {
            return false;
        }
        s.insert(path[i]);
        s.insert(path[i + 1]);
    }

    return s.size() == N;
}

bool IsTsCycle(vector<int> &path) {
    if (path.size() <= N + 1 || path[0] != path[path.size() - 1]) {
        return false;
    }

    unordered_set<int> s;

    for (int i = 0; i < path.size() - 1; ++i) {
        if (!IsConnect(path[i], path[i + 1])) {
            return false;
        }
        s.insert(path[i]);
        s.insert(path[i + 1]);
    }

    return s.size() == N;
}

int GetDis(int from, int to) {
    for (Node node : adj[from]) {
        if (node.to == to) {
            return node.dis;
        }
    }

    return -1;
}

int Dis(vector<int> &path) {
    int ans = 0;
    for (int i = 0; i < path.size() - 1; ++i) {
        int dis = GetDis(path[i], path[i + 1]);
        if (dis == -1) {
            return -1;
        } else {
            ans += dis;
        }
    }

    return ans;
}

int main() {
    cin >> N >> M;
    for (int i = 0; i < M; ++i) {
        int from, to, dis;
        cin >> from >> to >> dis;
        adj[from].emplace_back(from, to, dis);
        adj[to].emplace_back(to, from, dis);
    }

    int K;
    cin >> K;
    int min_dis = INT_MAX;
    int path_num = -1;

    for (int i = 1; i <= K; ++i) {
        int C;
        cin >> C;
        vector<int> path;
        for (int j = 0; j < C; ++j) {
            int v;
            cin >> v;
            path.emplace_back(v);
        }

        int dis = Dis(path);

        if (IsSimpleCycle(path)) {
            printf("Path %d: %d (TS simple cycle)\n", i, dis);
            if (dis < min_dis) {
                min_dis = dis;
                path_num = i;
            }
            continue;
        }

        if (IsTsCycle(path)) {
            printf("Path %d: %d (TS cycle)\n", i, dis);
            if (dis < min_dis) {
                min_dis = dis;
                path_num = i;
            }
            continue;
        }

        if (dis == -1) {
            printf("Path %d: NA (Not a TS cycle)\n", i);
        } else {
            printf("Path %d: %d (Not a TS cycle)\n", i, dis);
        }
    }

    printf("Shortest Dist(%d) = %d\n", path_num, min_dis);
    return 0;
}

//要保证每个顶点都被访问过，虽然长度大于N，但不是每个顶点都访问过