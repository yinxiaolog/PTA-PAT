#include <iostream>

using namespace std;

bool Compare(long long A, long long B, long long C) {

    if (A <= 0 && B >= 0 || A >= 0 && B <= 0) {
        return A + B > C;
    }

    if (A < 0 && B < 0) {
        if (A + B >= 0) {
            return false;
        } else {
            return A + B > C;
        }
    }

    if (A > 0 && B > 0) {
        if (A + B < 0) {
            return true;
        } else {
            return A + B > C;
        }
    }

    return false;
}

int main() {
    int N;
    cin >> N;
    for (int i = 1; i <= N; i++) {
        long long A, B, C;
        cin >> A >> B >> C;

        if (Compare(A, B, C)) {
            cout << "Case #" << i << ": true" << endl;
        } else {
            cout << "Case #" << i << ": false" << endl;
        }
    }
}