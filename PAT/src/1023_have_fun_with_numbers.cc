#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

const int kSize = 21;
int big_int[kSize];
int marked[10] = {0};

void Str2BigInt(string &str) {
    fill(big_int, big_int + kSize, -1);
    int carry_bit = 0;
    int index = 0;
    for (int i = str.length() - 1; i >= 0; i--) {
        int add = (str[i] - '0') * 2 + carry_bit;
        if (add >= 10) {
            carry_bit = 1;
            add -= 10;
        } else {
            carry_bit = 0;
        }

        big_int[index] = add;
        index++;
    }

    if (carry_bit == 1) {
        big_int[index] = carry_bit;
    }
}

void Hash(string &str) {
    for (char c : str) {
        marked[c - '0']++;
    }
}

bool Judge() {
    for (int i = kSize - 1; i >= 0; i--) {
        if (big_int[i] != -1) {
            marked[big_int[i]]--;
        }
    }

    for (int i = 0; i < 10; i++) {
        if (marked[i] != 0) {
            return false;
        }
    }

    return true;
}

int main() {
    string num;
    cin >> num;
    Hash(num);
    Str2BigInt(num);

    if (Judge()) {
        cout << "Yes" << endl;
    } else {
        cout << "No" << endl;
    }

    for (int i = kSize - 1; i >= 0; i--) {
        if (big_int[i] != -1) {
            cout << big_int[i];
        }
    }
    return 0;
}