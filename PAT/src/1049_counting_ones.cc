#include <iostream>

using namespace std;

int main() {
    int N;
    cin >> N;

    int a = 1;
    int ans = 0;
    while (N / a != 0) {
        int left = N / a / 10;
        int now = N / a % 10;
        int right = N % a;

        if (now == 0) {
            ans += left * a;
        }

        if (now == 1) {
            ans += left * a + right + 1;
        }

        if (now > 1) {
            ans += (left + 1) * a;
        }

        a *= 10;
    }

    cout << ans;
}