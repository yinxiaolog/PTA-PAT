#include <iostream>
#include <cstdio>
#include <cmath>

using namespace std;

const int kSize = 100001;

int hash_table[kSize] = {0};
int size;

bool IsPrime(int n) {
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

int FindSize(int n) {
    while (!IsPrime(n)) {
        n++;
    }

    return n;
}

bool Insert(int x) {
    int hash = x % size;
    if (hash_table[hash] == 0) {
        hash_table[hash] = x;
        return true;
    }

    for (int i = 1; i <= size; ++i) {
        int h = (hash + i * i) % size;
        if (hash_table[h] == 0) {
            hash_table[h] = x;
            return true;
        }
    }

    return false;
}

int Find(int x) {
    int hash = x % size;

    if (hash_table[hash] == x || hash_table[hash] == 0) {
        return 1;
    }

    int cnt = 1;
    for (int i = 1; i <= size; ++i) {
        int h = (hash + i * i) % size;
        cnt++;
        if (hash_table[h] == x || hash_table[h] == 0) {
            break;
        }
    }

    return cnt;
}

int main() {
    int MSize, N, M;
    cin >> MSize >> N >> M;
    size = FindSize(MSize);

    for (int i = 0; i < N; ++i) {
        int n;
        cin >> n;
        if (!Insert(n)) {
            printf("%d cannot be inserted.\n", n);
        }
    }

    int cnt = 0;
    for (int i = 0; i < M; ++i) {
        int n;
        cin >> n;
        cnt += Find(n);
    }

    printf("%.1f", (double) cnt / M);
}

//注意查询时返回的条件，查询为空或到结尾都应返回未查到