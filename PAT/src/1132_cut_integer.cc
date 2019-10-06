#include <iostream>
#include <string>

using namespace std;

bool Solve(string &s) {
    int k = s.length() / 2;
    long long n = stoll(s);
    string s1 = s.substr(0, k);
    string s2 = s.substr(k, k);
    long long a = stoll(s1);
    long long b = stoll(s2);

    if (a * b == 0) {
        return false;
    }

    return n % (a * b) == 0;
}

int main() {
    int N;
    cin >> N;
    for (int i = 0; i < N; ++i) {
        string s;
        cin >> s;
        if (Solve(s)) {
            cout << "Yes" << endl;
        } else {
            cout << "No" << endl;
        }
    }

    return 0;
}