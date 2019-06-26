#include <iostream>
#include <string>
#include <cmath>

using namespace std;

int main() {
    string str;
    cin >> str;
    int n2 = ceil((str.length() + 2) / 3.0);
    int n1 = (str.length() + 2 - n2) / 2;

    while (n1 * 2 + n2 - 2 != str.length()) {
        n2++;
    }

    for (int i = 0; i < n1 - 1; i++) {
        cout << str[i];
        for (int j = 0; j < n2 - 2; j++) {
            cout << ' ';
        }
        cout << str[str.length() - 1 - i];
        cout << endl;
    }

    for (int i = n1 - 1; i < n1 + n2 - 1; i++) {
        cout << str[i];
    }
    return 0;
}