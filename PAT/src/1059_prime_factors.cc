#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

const int kSize = 100000;

vector<int> primes;

bool IsPrime(int x) {
    if (x < 2) {
        return false;
    }

    int sq = (int) sqrt(x);

    for (int i = 2; i <= sq; i++) {
        if (x % i != 0) {
            return false;
        }
    }

    return true;
}

void FindPrime() {
    bool primes_tmp[kSize];

    fill(primes_tmp, primes_tmp + kSize, true);
    primes_tmp[0] = primes_tmp[1] = false;
    for (int i = 2; i < kSize; i++) {
        if (primes_tmp[i] && IsPrime(i)) {
            for (int j = i + i; j < kSize; j += i) {
                primes_tmp[j] = false;
            }
        }
    }

    for (int i = 0; i < kSize; i++) {
        if (primes_tmp[i]) {
            primes.push_back(i);
        }
    }
}

map<int, int> Factor(int x) {
    int index = 0;
    map<int, int> factors;

    while (x != 1) {
        while (x % primes[index] == 0) {
            if (factors.find(primes[index]) == factors.end()) {
                factors[primes[index]] = 1;
            } else {
                factors[primes[index]] += 1;
            }

            x /= primes[index];
        }

        index++;
    }

    return factors;
}

int main() {
    int N;
    cin >> N;
    if (N < 2) {
        cout << N << "=" << N;
        return 0;
    }
    FindPrime();
    map<int, int> factors = Factor(N);
    string ans;

    for (auto it = factors.begin(); it != factors.end(); it++) {
        if (it == factors.begin()) {
            if (it->second > 1) {
                ans += to_string(it->first) + "^" + to_string(it->second);
            } else {
                ans += to_string(it->first);
            }
        } else {
            if (it->second > 1) {
                ans += "*" + to_string(it->first) + "^" + to_string(it->second);
            } else {
                ans += "*" + to_string(it->first);
            }
        }
    }

    cout << N << "=" << ans;
}