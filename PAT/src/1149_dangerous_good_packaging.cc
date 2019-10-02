#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>

using namespace std;

unordered_map<int, vector<int> > dangerous_goods;

bool Solve(vector<int> &list) {
    for (int i : list) {
        if (dangerous_goods.find(i) != dangerous_goods.end()) {
            for (int j : dangerous_goods[i]) {
                if (find(list.begin(), list.end(), j) != list.end()) {
                    return false;
                }
            }
        }
    }

    return true;
}

int main() {
    int N, M;
    cin >> N >> M;

    for (int i = 0; i < N; ++i) {
        int a, b;
        cin >> a >> b;
        dangerous_goods[a].emplace_back(b);
        dangerous_goods[b].emplace_back(a);
    }

    for (int i = 0; i < M; ++i) {
        int K;
        cin >> K;
        vector<int> list;
        for (int j = 0; j < K; ++j) {
            int G;
            cin >> G;
            list.emplace_back(G);
        }

        if (Solve(list)) {
            cout << "Yes" << endl;
        } else {
            cout << "No" << endl;
        }
    }
}