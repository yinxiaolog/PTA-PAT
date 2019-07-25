#include <iostream>
#include <string>

using namespace std;

int hash_table[256] = {0};

int main() {
    string beads_shop, beads_eva;
    cin >> beads_shop >> beads_eva;
    for (char c : beads_eva) {
        hash_table[(int) c]++;
    }

    for (char c : beads_shop) {
        hash_table[(int) c]--;
    }

    int ans = 0;
    for (int i : hash_table) {
        if (i > 0) {
            ans += i;
        }
    }

    if (ans == 0) {
        cout << "Yes" << " " << beads_shop.length() - beads_eva.length();
    } else {
        cout << "No" << " " << ans;
    }
    return 0;
}