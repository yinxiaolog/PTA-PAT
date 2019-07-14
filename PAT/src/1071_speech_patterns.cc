#include <iostream>
#include <map>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    string word;
    getline(cin, word);
    transform(word.begin(), word.end(), word.begin(), ::tolower);

    map<string, int> string2int;
    for (int i = 0; i < word.length();) {
        string str;
        while (i < word.length() && (word[i] >= 'a' && word[i] <= 'z' || word[i] >= '0' && word[i] <= '9')) {
            str += word[i];
            i++;
        }

        if (!str.empty()) {
            if (string2int.find(str) == string2int.end()) {
                string2int[str] = 1;
            } else {
                string2int[str]++;
            }
        }

        while (i < word.length() && !(word[i] >= 'a' && word[i] <= 'z' || word[i] >= '0' && word[i] <= '9')) {
            i++;
        }
    }

    string ans;
    int max = -1;

    for (auto p : string2int) {
        if (p.second > max) {
            max = p.second;
            ans = p.first;
        }
    }

    cout << ans << ' ' << max;
    return 0;
}