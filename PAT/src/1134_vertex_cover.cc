#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

vector<pair<int, int> > edges;
int N, M;
unordered_map<int, vector<int> > hash_map;

bool IsVertexCover(vector<int> &list) {
    bool marked[10000] = {false};
    for (int i : list) {
        for (int j : hash_map[i]) {
            marked[j] = true;
        }
    }

    for (int i = 0; i < M; ++i) {
        if (!marked[i]) {
            return false;
        }
    }

    return true;
}

int main() {
    cin >> N >> M;

    for (int i = 0; i < M; ++i) {
        int from, to;
        cin >> from >> to;
        hash_map[from].emplace_back(i);
        hash_map[to].emplace_back(i);
    }

    int K;
    cin >> K;
    for (int i = 0; i < K; ++i) {
        int v;
        cin >> v;
        vector<int> vertex;
        for (int i = 0; i < v; ++i) {
            int val;
            cin >> val;
            vertex.emplace_back(val);
        }

        if (IsVertexCover(vertex)) {
            cout << "Yes" << endl;
        } else {
            cout << "No" << endl;
        }
    }

    return 0;
}

//用hash优化，直接遍历每一条边，在顶点集查找会超时