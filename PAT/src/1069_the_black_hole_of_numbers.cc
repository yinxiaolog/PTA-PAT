#include <iostream>
#include <algorithm>
#include <functional>

using namespace std;

int Max(int x) {
    int num[4];

    for (int i = 0; i < 4; i++) {
        num[i] = x % 10;
        x /= 10;
    }

    sort(num, num + 4, greater<int>());

    int sum = 0;
    for (int i = 0; i < 4; i++) {
        sum = sum * 10 + num[i];
    }

    return sum;
}

int Min(int x) {
    int num[4];

    for (int i = 0; i < 4; i++) {
        num[i] = x % 10;
        x /= 10;
    }

    sort(num, num + 4);

    int sum = 0;
    for (int i = 0; i < 4; i++) {
        sum = sum * 10 + num[i];
    }

    return sum;
}

int main() {
    int N;
    cin >> N;

    if (Max(N) == Min(N)) {
        printf("%04d - %04d = %04d", N, N, 0);
    } else {
        int max = Max(N);
        int min = Min(N);
        int diff = max - min;
        while (diff != 6174) {
            printf("%04d - %04d = %04d\n", max, min, diff);
            max = Max(diff);
            min = Min(diff);
            diff = max - min;
        }

        printf("%04d - %04d = %04d\n", max, min, diff);
    }
    return 0;
}