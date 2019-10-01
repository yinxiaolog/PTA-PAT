#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>
#include <unordered_map>

using namespace std;

struct Node {
    string info;
    string level;
    string site;
    string date;
    string number;
    int score;

    Node(string &_info, int _score) {
        info = _info;
        level = _info.substr(0, 1);
        site = _info.substr(1, 3);
        date = _info.substr(4, 6);
        number = _info.substr(10, 3);
        score = _score;
    }
};

vector<Node> list;

struct SiteNt {
    string site;
    int num;

    SiteNt() {}

    SiteNt(string _site, int _num) {
        site = _site;
        num = _num;
    }
};

bool CmpNode(Node &one, Node &another) {
    if (one.score != another.score) {
        return one.score > another.score;
    }

    return one.info < another.info;
}

bool CmpSiteNt(SiteNt &one, SiteNt &another) {
    if (one.num != another.num) {
        return one.num > another.num;
    }

    return one.site < another.site;
}

vector<Node> QueryByLevel(string &level) {
    vector<Node> ans;
    for (auto &node : list) {
        if (node.level == level) {
            ans.emplace_back(node);
        }
    }

    sort(ans.begin(), ans.end(), CmpNode);
    return ans;
}

pair<int, int> QueryBySite(string &site) {
    int total = 0;
    int num = 0;
    for (auto &node : list) {
        if (node.site == site) {
            total += node.score;
            num++;
        }
    }

    return make_pair(total, num);
}

vector<SiteNt> QueryByDate(string &date) {
    unordered_map<string, int> m;
    for (auto &node : list) {
        if (node.date == date) {
            if (m.find(node.site) == m.end()) {
                m[node.site] = 1;
            } else {
                m[node.site] = m[node.site] + 1;
            }
        }
    }

    vector<SiteNt> ans;
    for (auto &siteNt : m) {
        ans.emplace_back(siteNt.first, siteNt.second);
    }

    sort(ans.begin(), ans.end(), CmpSiteNt);
    return ans;
}

int main() {
    int N, M;
    cin >> N >> M;

    for (int i = 0; i < N; ++i) {
        string info;
        int score;
        cin >> info >> score;
        list.emplace_back(info, score);
    }

    for (int i = 0; i < M; ++i) {
        int index;
        string str;
        cin >> index >> str;
        printf("Case %d: %d %s\n", i + 1, index, str.c_str());

        if (index == 1) {
            vector<Node> ans = QueryByLevel(str);
            if (ans.empty()) {
                printf("NA\n");
            }
            for (auto &node : ans) {
                printf("%s %d\n", node.info.c_str(), node.score);
            }
        }

        if (index == 2) {
            pair<int, int> ans = QueryBySite(str);
            if (ans.second == 0) {
                printf("NA\n");
            } else {
                printf("%d %d\n", ans.second, ans.first);
            }
        }

        if (index == 3) {
            vector<SiteNt> ans = QueryByDate(str);
            if (ans.empty()) {
                printf("NA\n");
            }
            for (auto &siteNt : ans) {
                printf("%s %d\n", siteNt.site.c_str(), siteNt.num);
            }
        }
    }

    return 0;
}