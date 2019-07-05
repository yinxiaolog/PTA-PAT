#include <iostream>
#include <string>
#include <vector>

using namespace std;

const int kSize = 55;

vector<string> cards;

void init() {
    cards.emplace_back("");
    for (int i = 0; i < 4; i++) {
        string str;
        if (i == 0) {
            str = "S";
        }
        if (i == 1) {
            str = "H";
        }
        if (i == 2) {
            str = "C";
        }
        if (i == 3) {
            str = "D";
        }

        for (int j = 1; j <= 13; j++) {
            cards.emplace_back(str + to_string(j));
        }
    }

    cards.emplace_back("J1");
    cards.emplace_back("J2");
}

int main() {
    int K;
    cin >> K;
    int order[kSize];
    for (int i = 1; i < kSize; i++) {
        int tmp;
        cin >> tmp;
        order[i] = tmp;
    }
    init();
    vector<string> ans = cards;
    for (int i = 0; i < K; i++) {
        cards = ans;
        for (int j = 1; j < kSize; j++) {
            ans[order[j]] = cards[j];
        }
    }

    for (int i = 1; i < ans.size(); i++) {
        if (i == 1) {
            cout << ans[i];
        } else {
            cout << " " << ans[i];
        }
    }
}