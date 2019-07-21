#include <iostream>
#include <string>
#include <cstring>

using namespace std;

bool hash_table[256] = {false};

int main() {
    string original;
    string typed_out;
    cin >> original >> typed_out;
    for (int i = 0; i < original.length(); i++) {
        original[i] = toupper(original[i]);
    }

    for (int i = 0; i < typed_out.length(); i++) {
        typed_out[i] = toupper(typed_out[i]);
    }

    for (char c : original) {
        hash_table[c] = true;
    }

    for (char c : typed_out) {
        hash_table[c] = false;
    }

    bool ans[256] = {false};

    for (char c : original) {
        if (hash_table[c]) {
            if (!ans[c]) {
                cout << c;
            }
            ans[c] = true;
        }
    }

    return 0;
}