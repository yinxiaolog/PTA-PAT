#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <cmath>
#include <queue>
#include <cstdio>

using namespace std;
const int kSize = 100;

vector<int> edge_to[kSize];
vector<int> path, tmp_path;
int s = 1;

void Dfs(int v) {
    tmp_path.push_back(v);

    if (v == s) {
        for (int i : tmp_path) {
            cout << i << " ";
        }
        tmp_path.pop_back();
        cout << endl;
        return;
    }

    for (int i : edge_to[v]) {
        Dfs(i);
    }

    tmp_path.pop_back();
}

int main() {
    printf("%.1f", (double) 3);
}