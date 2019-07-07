#include <iostream>
#include <string>

using namespace std;

int main() {
    string S1, S2;
    getline(cin, S1);
    getline(cin, S2);

    for (int i = 0; i < S2.length(); i++) {
        while (S1.find(S2[i]) != string::npos) {
            int index = S1.find(S2[i]);
            S1.erase(index, 1);
        }
    }

    cout << S1;
}