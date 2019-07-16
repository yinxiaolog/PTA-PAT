#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

const int kSize = 10001;
const int kProblems = 6;

int N, K, M;

int full_mark[kProblems];

struct Node {
    int id;
    int problems[kProblems];
    int rank;
    int total;

    Node() {
        id = -1;
        fill(problems, problems + kProblems, -2);
    }

    int Total() {
        int ans = 0;
        for (int i = 1; i <= K; i++) {
            if (problems[i] <= 0) {
                ans += 0;
            } else {
                ans += problems[i];
            }
        }
        total = ans;
        return ans;
    }

    int Perfect() {
        int ans = 0;
        for (int i = 1; i <= K; i++) {
            if (problems[i] == full_mark[i]) {
                ans++;
            }
        }

        return ans;
    }

    bool IsRanked() {
        for (int i = 1; i <= K; i++) {
            if (problems[i] >= 0) {
                return true;
            }
        }

        return false;
    }
};

Node students[kSize];

bool Cmp(Node &one, Node &another) {
    if (one.Total() < another.Total()) {
        return false;
    }

    if (one.Total() > another.Total()) {
        return true;
    }

    if (one.Perfect() < another.Perfect()) {
        return false;
    }

    if (one.Perfect() > another.Perfect()) {
        return true;
    }

    return one.id < another.id;
}

vector<Node> Rank() {
    vector<Node> ans;
    for (int i = 0; i < kSize; i++) {
        if (students[i].id != -1 && students[i].IsRanked()) {
            ans.push_back(students[i]);
        }
    }

    sort(ans.begin(), ans.end(), Cmp);
    ans[0].rank = 1;
    for (int i = 1; i < ans.size(); i++) {
        if (ans[i].total == ans[i - 1].total) {
            ans[i].rank = ans[i - 1].rank;
        } else {
            ans[i].rank = i + 1;
        }
    }

    return ans;
}

int main() {
    cin >> N >> K >> M;

    for (int i = 1; i <= K; i++) {
        cin >> full_mark[i];
    }
    for (int i = 0; i < M; i++) {
        int id, problem, score;
        cin >> id >> problem >> score;
        students[id].id = id;
        if (score > students[id].problems[problem]) {
            students[id].problems[problem] = score;
        }
    }

    vector<Node> ans = Rank();
    for (Node node : ans) {
        cout << node.rank;
        printf(" %05d %d", node.id, node.Total());
        for (int i = 1; i <= K; i++) {
            if (node.problems[i] == -2) {
                cout << " -";
            } else if (node.problems[i] > 0) {
                cout << ' ' << node.problems[i];
            } else {
                cout << ' ' << 0;
            }
        }

        cout << endl;
    }
    return 0;
}