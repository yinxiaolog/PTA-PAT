#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

unordered_map<string, vector<string> > hash_map;

vector<string> GetFriends(string &id) {
    vector<string> ans;
    for (string &s : hash_map[id]) {
        if (id[0] == s[0]) {
            ans.emplace_back(s);
        }
    }

    return ans;
}

bool IsFriend(string &a, string &b) {
    for (string &s : hash_map[a]) {
        if (s == b) {
            return true;
        }
    }

    return false;
}

vector<pair<string, string> > Solve(string &a, string &b) {
    vector<string> friends_a = GetFriends(a);
    vector<string> friends_b = GetFriends(b);
}

int main() {
    int N, M;
    cin >> N >> M;

    for (int i = 0; i < M; ++i) {
        string from, to;
        cin >> from >> to;
        hash_map[from].emplace_back(to);
        hash_map[to].emplace_back(from);
    }

    for (auto kv : hash_map) {
        cout << kv.first << ':' << endl;
        for (string s : kv.second) {
            cout << s << ' ';
        }
        cout << endl;
    }
    int K;
    cin >> K;
}