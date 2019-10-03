#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

int main() {
    int N;
    cin >> N;
    unordered_map<int, bool> m;
    for (int i = 0; i < N; ++i) {
        int n;
        cin >> n;
        m[n] = true;
    }

    for (int i = 1;; ++i) {
        if (m.find(i) == m.end()) {
            cout << i;
            return 0;
        }
    }
}