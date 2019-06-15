#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int main() {
    int a, b;
    cin >> a >> b;
    int sum = a + b;

    string ans = to_string(abs(sum));

    for (int i = ans.length() - 1 - 3; i >= 0; i -= 3) {
        ans.insert(i + 1, ",");
    }
    if (sum < 0) {
        ans.insert(0, "-");
    }
    cout << ans;
}