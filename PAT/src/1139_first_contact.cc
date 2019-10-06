#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

unordered_map<string, vector<string> > hash_map;

vector<string> GetFriends(string &id) {
    vector<string> ans;
    for (string s : hash_map[id]) {
        if (s.length() == id.length()) {
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

    vector<pair<string, string> > ans;
    for (string &s1 : friends_a) {
        for (string &s2 : friends_b) {
            string tmp_a = s1;
            string tmp_b = s2;
            if (s1 != b && s2 != a && IsFriend(s1, s2)) {
                if (s1[0] == '-') {
                    tmp_a = s1.substr(1, 4);
                }
                if (s2[0] == '-') {
                    tmp_b = s2.substr(1, 4);
                }
                ans.emplace_back(tmp_a, tmp_b);
            }
        }
    }

    sort(ans.begin(), ans.end());
    return ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int N, M;
    cin >> N >> M;

    for (int i = 0; i < M; ++i) {
        string from, to;
        cin >> from >> to;
        hash_map[from].emplace_back(to);
        hash_map[to].emplace_back(from);
    }

    int K;
    cin >> K;

    for (int i = 0; i < K; i++) {
        string a, b;
        cin >> a >> b;
        vector<pair<string, string> > ans = Solve(a, b);
        cout << ans.size() << endl;
        for (auto &p : ans) {
            cout << p.first << ' ' << p.second << endl;
        }
    }

    return 0;
}

//判断同性要用字符串长度，相等为同性，300ms不稳定，有时候会通不过