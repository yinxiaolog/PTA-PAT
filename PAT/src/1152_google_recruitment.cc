#include <iostream>
#include <string>
#include <cmath>

using namespace std;

bool isPrime(long long n) {
    if (n < 2) {
        return false;
    }

    if (n == 2 || n == 3) {
        return true;
    }

    if (n % 6 != 1 && n % 6 != 5) {
        return false;
    }

    int sq = sqrt(n);
    for (int i = 5; i <= sq; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }

    return true;
}

int main() {
    int L, K;
    cin >> L >> K;
    string str;
    cin >> str;

    for (int i = 0; i <= str.length() - K; ++i) {
        string sub = str.substr(i, K);
        if (isPrime(stoll(sub))) {
            cout << sub;
            return 0;
        }
    }

    cout << 404;
}