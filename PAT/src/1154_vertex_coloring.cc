#include <iostream>
#include <cstdio>
#include <vector>
#include <unordered_set>

using namespace std;

const int kSize = 10001;

int N, M;
int color[kSize];

int isKColor(vector<pair<int, int> > &v) {
    unordered_set<int> s;

    for (auto p : v) {
        if (color[p.first] == color[p.second]) {
            return -1;
        }
    }

    for (int i = 0; i < N; ++i) {
        s.insert(color[i]);
    }

    return s.size();
}

int main() {
    cin >> N >> M;
    vector<pair<int, int> > edges;

    for (int i = 0; i < M; ++i) {
        int from, to;
        cin >> from >> to;
        edges.emplace_back(make_pair(from, to));
    }

    int K;
    cin >> K;

    for (int i = 0; i < K; ++i) {
        for (int j = 0; j < N; ++j) {
            cin >> color[j];
        }

        int k = isKColor(edges);
        if (k == -1) {
            cout << "No" << endl;
        } else {
            printf("%d-coloring\n", k);
        }
    }

    return 0;
}