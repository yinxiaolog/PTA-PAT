#include <iostream>
#include <unordered_set>

using namespace std;

const int kSize = 51;

unordered_set<int> sets[kSize];

double Solve(int one, int another) {
    int Nc = 0, Nt = 0;

    for (int i : sets[one]) {
        if (sets[another].find(i) != sets[another].end()) {
            Nc++;
        }
    }
    Nt = sets[one].size() + sets[another].size() - Nc;
    return Nc / (double) Nt;
}

int main() {
    int N;
    cin >> N;
    for (int i = 1; i <= N; i++) {
        int M;
        cin >> M;
        for (int j = 0; j < M; j++) {
            int x;
            cin >> x;
            sets[i].insert(x);
        }
    }

    int K;
    cin >> K;
    for (int i = 0; i < K; i++) {
        int one, another;
        cin >> one >> another;
        double ans = Solve(one, another);
        printf("%.1f%%\n", ans * 100);
    }
    return 0;
}