#include <iostream>

using namespace std;

int main() {
    string D;
    int N;
    cin >> D >> N;

    for (int i = 1; i < N; ++i) {
        string tmp;
        for (int j = 0; j < D.length();) {
            char c = D[j];
            int cnt = 0;
            while (c == D[j]) {
                j++;
                cnt++;
            }
            tmp += c;
            tmp += to_string(cnt);
        }
        D = tmp;
    }

    cout << D << endl;
    return 0;
}