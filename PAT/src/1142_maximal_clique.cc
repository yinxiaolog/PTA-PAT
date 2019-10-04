#include <iostream>
#include <vector>

using namespace std;

const int kSize = 201;

vector<int> adj[kSize];
int Nv;

bool IsConnect(int from, int to) {
    for (int i : adj[from]) {
        if (i == to) {
            return true;
        }
    }

    return false;
}

bool IsClique(vector<int> &list) {
    for (int i : list) {
        for (int j : list) {
            if (i != j && !IsConnect(i, j)) {
                return false;
            }
        }
    }

    return true;
}

bool IsMaxClique(vector<int> &list) {
    bool marked[kSize];
    fill(marked, marked + kSize, true);
    for (int i : list) {
        marked[i] = false;
    }

    for (int i = 1; i <= Nv; ++i) {
        if (marked[i]) {
            bool flag = true;
            for (int j : list) {
                if (!IsConnect(i, j)) {
                    flag = false;
                }
            }

            if (flag) {
                return false;
            }
        }
    }

    return true;
}

int main() {
    int Ne;
    cin >> Nv >> Ne;

    for (int i = 0; i < Ne; ++i) {
        int from, to;
        cin >> from >> to;
        adj[from].emplace_back(to);
        adj[to].emplace_back(from);
    }

    int M;
    cin >> M;
    for (int i = 0; i < M; ++i) {
        int K;
        cin >> K;
        vector<int> v;
        for (int j = 0; j < K; ++j) {
            int x;
            cin >> x;
            v.emplace_back(x);
        }

        if (IsClique(v) && IsMaxClique(v)) {
            cout << "Yes" << endl;
        } else if (IsClique(v)) {
            cout << "Not Maximal" << endl;
        } else {
            cout << "Not a Clique" << endl;
        }
    }

    return 0;
}