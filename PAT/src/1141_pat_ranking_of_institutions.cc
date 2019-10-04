#include <iostream>
#include <vector>
#include <unordered_map>
#include <string>
#include <algorithm>

using namespace std;

struct Node {
    string school;
    int tws;
    double s;
    int ns;
    int rank;
};

double CalTws(string &id, int score) {
    if (id[0] == 'A') {
        return score;
    }

    if (id[0] == 'B') {
        return (double) score / 1.5;
    }

    if (id[0] == 'T') {
        return (double) score * 1.5;
    }

    return 0;
}

bool Cmp(Node &one, Node &another) {
    if (one.tws != another.tws) {
        return one.tws > another.tws;
    }

    if (one.ns != another.ns) {
        return one.ns < another.ns;
    }

    return one.school < another.school;
}

vector<Node> Rank(unordered_map<string, Node> &m) {
    vector<Node> list;
    for (auto &kv : m) {
        kv.second.tws = (int) kv.second.s;
        list.emplace_back(kv.second);
    }
    sort(list.begin(), list.end(), Cmp);

    list[0].rank = 1;
    for (int i = 1; i < list.size(); ++i) {
        if (list[i].tws == list[i - 1].tws) {
            list[i].rank = list[i - 1].rank;
        } else {
            list[i].rank = i + 1;
        }
    }

    return list;
}

int main() {
    int N;
    cin >> N;
    unordered_map<string, Node> m;

    for (int i = 0; i < N; ++i) {
        string id;
        double score;
        string school;
        cin >> id >> score >> school;
        if (id[0] == 'B') {
            score /= 1.5;
        }

        if (id[0] == 'T') {
            score *= 1.5;
        }
        transform(school.begin(), school.end(), school.begin(), ::tolower);
        m[school].s += score;
        m[school].ns++;
        m[school].school = school;
    }

    vector<Node> ans = Rank(m);
    cout << ans.size() << endl;
    for (Node &node : ans) {
        cout << node.rank << ' ' << node.school << ' ' << (int) node.tws << ' ' << node.ns << endl;
    }

    return 0;
}

//先用double把成绩相加，再转回int排序，double直接排序会使tws相等失效