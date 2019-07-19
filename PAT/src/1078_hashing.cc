#include <iostream>
#include <cmath>

using namespace std;

const int kSize = 10000;

int M, N;
bool hash_table[kSize] = {false};

bool IsPrime(int x) {
    if (x < 2) {
        return false;
    }

    int sq = (int) sqrt(x);

    for (int i = 2; i <= sq; i++) {
        if (x % i == 0) {
            return false;
        }
    }

    return true;
}

int FindPrime(int x) {
    while (!IsPrime(x)) {
        x++;
    }

    return x;
}

int Hash(int x) {
    int hash_code = x % M;
    if (!hash_table[hash_code]) {
        hash_table[hash_code] = true;
        return hash_code;
    }

    for (int i = 1; i <= M; i++) {
        int tmp = (hash_code + i * i) % M;
        if (!hash_table[tmp]) {
            hash_table[tmp] = true;
            return tmp;
        }
    }

    return -1;
}

int main() {
    cin >> M >> N;
    M = FindPrime(M);

    for (int i = 0; i < N; i++) {
        int x;
        cin >> x;
        int hash_code = Hash(x);
        if (i == 0) {
            if (hash_code != -1) {
                cout << hash_code;
            } else {
                cout << '-';
            }
        } else {
            if (hash_code != -1) {
                cout << ' ' << hash_code;
            } else {
                cout << " -";
            }
        }
    }
    return 0;
}