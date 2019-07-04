#include <iostream>
#include <vector>

using namespace std;

const int kSize = 100001;

int main() {
    int N;
    cin >> N;
    vector<int> v;
    int hash[kSize] = {0};
    for (int i = 0; i < N; i++) {
        int x;
        cin >> x;
        v.push_back(x);
        hash[x]++;
    }

    int ans = -1;
    for (int i : v) {
        if (hash[i] == 1) {
            ans = i;
            break;
        }
    }

    if (ans != -1) {
        cout << ans;
    } else {
        cout << "None";
    }
}