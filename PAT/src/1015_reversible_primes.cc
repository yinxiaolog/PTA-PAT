#include <iostream>
#include <cmath>
#include <queue>

using namespace std;

int Reversal(int n, int radix) {
    queue<int> remainders;
    while (n > 0) {
        remainders.push(n % radix);
        n /= radix;
    }

    int ans = 0;
    while (!remainders.empty()) {
        ans = ans * radix + remainders.front();
        remainders.pop();
    }

    return ans;
}

bool IsPrime(int n) {
    if (n < 2) {
        return false;
    }
    int s = (int) sqrt(n);

    for (int i = 2; i <= s; i++) {
        if (n % i == 0) {
            return false;
        }
    }

    return true;
}

int main() {
    int N, D;

    while (true) {
        cin >> N;
        if (N < 0) {
            break;
        }
        cin >> D;
        if (IsPrime(N) && IsPrime(Reversal(N, D))) {
            cout << "Yes" << endl;
        } else {
            cout << "No" << endl;
        }
    }
    return 0;
}