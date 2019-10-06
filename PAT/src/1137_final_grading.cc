#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <cmath>

using namespace std;

struct Node {
    string id;
    int online = -1;
    int mid = -1;
    int final = -1;
    int score;
};

bool Cmp(Node &one, Node &another) {
    return one.score != another.score ? one.score > another.score : one.id < another.id;
}

vector<Node> Solve(unordered_map<string, Node> &hash_map) {
    vector<Node> ans;
    for (auto &kv : hash_map) {
        if (kv.second.online >= 200) {
            if (kv.second.mid > kv.second.final) {
                kv.second.score = (int) lround(0.4 * kv.second.mid + 0.6 * kv.second.final);
            } else {
                kv.second.score = kv.second.final;
            }

            if (kv.second.score >= 60) {
                kv.second.id = kv.first;
                ans.push_back(kv.second);
            }
        }
    }

    sort(ans.begin(), ans.end(), Cmp);
    return ans;
}

int main() {
    int P, M, N;
    cin >> P >> M >> N;
    unordered_map<string, Node> hash_map;
    for (int i = 0; i < P; ++i) {
        string id;
        int score;
        cin >> id >> score;
        hash_map[id].online = score;
    }
    for (int i = 0; i < M; ++i) {
        string id;
        int score;
        cin >> id >> score;
        hash_map[id].mid = score;
    }
    for (int i = 0; i < N; ++i) {
        string id;
        int score;
        cin >> id >> score;
        hash_map[id].final = score;
    }

    vector<Node> ans = Solve(hash_map);
    for (Node &node : ans) {
        cout << node.id << ' ' << node.online << ' ' << node.mid << ' ' << node.final << ' ' << node.score << endl;
    }

    return 0;
}