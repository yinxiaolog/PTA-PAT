#include <iostream>
#include <vector>
#include <cstdio>

using namespace std;

const int kSize = 1001;

struct Node {
    int exp;
    double coe;

    Node(int _exp, double _coe) {
        exp = _exp;
        coe = _coe;
    }
};

vector<Node> p;

int main() {
    int K;
    double ans[kSize * 2] = {0};
    cin >> K;
    for (int i = 0; i < K; i++) {
        int exp;
        double coe;
        cin >> exp >> coe;
        p.emplace_back(exp, coe);
    }

    cin >> K;

    for (int i = 0; i < K; i++) {
        int exp;
        double coe;
        cin >> exp;
        cin >> coe;

        for (int j = 0; j < p.size(); j++) {
            ans[exp + p[j].exp] += coe * p[j].coe;
        }
    }

    int count = 0;
    for (int i = kSize * 2 - 1; i >= 0; i--) {
        if (ans[i] != 0) {
            count++;
        }
    }

    cout << count;
    for (int i = kSize * 2 - 1; i >= 0; i--) {
        if (ans[i] != 0) {
            printf(" %d %.1f", i, ans[i]);
        }
    }
    return 0;
}