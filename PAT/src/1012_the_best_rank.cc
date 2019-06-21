#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <cmath>

using namespace std;

struct Node {
    int id;
    int grade;
    int rank;

    Node(int _id, int _grade) {
        id = _id;
        grade = _grade;
    }
};

vector<Node> C, M, E, A;

bool cmp(Node &node_A, Node &node_B) {
    return node_A.grade > node_B.grade;
}

void Rank(vector<Node> &v) {
    sort(v.begin(), v.end(), cmp);
    v[0].rank = 1;
    for (int i = 1; i < v.size(); i++) {
        if (v[i].grade == v[i - 1].grade) {
            v[i].rank = v[i - 1].rank;
        } else {
            v[i].rank = i + 1;
        }
    }
}

int FindRank(vector<Node> &v, int id) {
    for (int i = 0; i < v.size(); i++) {
        if (v[i].id == id) {
            return v[i].rank;
        }
    }

    return -1;
}

pair<int, char> BestRank(int id) {
    int c = FindRank(C, id);
    if (c == -1) {
        return make_pair(-1, 'N');
    }

    int m = FindRank(M, id);
    int e = FindRank(E, id);
    int a = FindRank(A, id);
    int ans = min(min(min(c, m), e), a);

    if (ans == a) {
        return make_pair(ans, 'A');
    }
    if (ans == c) {
        return make_pair(ans, 'C');
    }
    if (ans == m) {
        return make_pair(ans, 'M');
    }
    if (ans == e) {
        return make_pair(ans, 'E');
    }
}

int main() {
    int n, m;
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        int id, cpl, math, english, avg;
        cin >> id >> cpl >> math >> english;
        avg = (int) round((cpl + math + english) / 3.0);
        C.emplace_back(id, cpl);
        M.emplace_back(id, math);
        E.emplace_back(id, english);
        A.emplace_back(id, avg);
    }

    Rank(C);
    Rank(M);
    Rank(E);
    Rank(A);
    vector<int> queries;
    for (int i = 0; i < m; i++) {
        int id;
        cin >> id;
        pair<int, char> ans = BestRank(id);
        if (ans.first == -1) {
            cout << "N/A" << endl;
        } else {
            cout << ans.first << " " << ans.second << endl;
        }
    }
    return 0;
}
