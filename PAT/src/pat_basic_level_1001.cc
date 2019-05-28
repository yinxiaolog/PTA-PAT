#include <iostream>

using namespace std;

void PrintTopOrBottom(int n, char c) {
    for (int i = 0; i < n; ++i) {
        cout << c;
    }

    cout << endl;
}

void PrintMiddle(int n, char c) {
    cout << c;

    for (int i = 1; i < n - 1; i++) {
        cout << ' ';
    }

    cout << c;
    cout << endl;
}

int main() {
    int n;
    char c;
    cin >> n >> c;

    int rows = n % 2 == 0 ? n / 2 : n / 2 + 1;

    PrintTopOrBottom(n, c);
    for (int i = 0; i < rows - 2; i++) {
        PrintMiddle(n, c);
    }
    PrintTopOrBottom(n, c);

    return 0;
}