#include <iostream>
#include <cstdio>
#include <algorithm>

using namespace std;

int N;
double D;

struct Cake {
    double inventory;
    double value;
    double price;
} cakes[1000];

bool cmp(Cake cake1, Cake cake2) {
    return cake1.price > cake2.price;
}

double solve() {
    sort(cakes, cakes + N, cmp);

    double ans = 0;

    for (int i = 0; i < N; i++) {
        if (cakes[i].inventory > D) {
            ans += D * cakes[i].price;
            break;
        } else {
            ans += cakes[i].value;
            D -= cakes[i].inventory;
        }
    }

    return ans;
}

int main() {
    cin >> N >> D;
    for (int i = 0; i < N; i++) {
        cin >> cakes[i].inventory;
    }

    for (int i = 0; i < N; i++) {
        cin >> cakes[i].value;
    }

    for (int i = 0; i < N; i++) {
        cakes[i].price = cakes[i].value / cakes[i].inventory;
    }

    printf("%.2f", solve());
    return 0;
}