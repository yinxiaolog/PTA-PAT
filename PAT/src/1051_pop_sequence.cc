#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int M, N, K;

bool IsPop(vector<int> &v) {
    int x = 1;
    stack<int> s;

    for (int i : v) {
        while (x <= i) {
            s.push(x++);
        }

        if (s.size() > M) {
            return false;
        }

        if (i <= x) {
            if (i == s.top()) {
                s.pop();
            } else {
                return false;
            }
        }
    }

    return true;
}

int main() {
    cin >> M >> N >> K;


    for (int i = 0; i < K; i++) {
        vector<int> pop_sequence;
        for (int j = 0; j < N; j++) {
            int x;
            cin >> x;
            pop_sequence.push_back(x);
        }

        if (IsPop(pop_sequence)) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl;
        }
    }
    return 0;
}