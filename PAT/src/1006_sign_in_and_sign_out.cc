#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

struct Node {
    string id;
    string time;

    Node(string _id, string _time) {
        id = _id;
        time = _time;
    }
};

bool cmp1(Node &a, Node &b) {
    return a.time < b.time;
}

bool cmp2(Node &a, Node &b) {
    return a.time > b.time;
}

int main() {
    int N;
    cin >> N;
    vector<Node> in, out;

    for (int i = 0; i < N; i++) {
        string id, sign_in_time, sign_out_time;
        cin >> id >> sign_in_time >> sign_out_time;
        in.emplace_back(Node(id, sign_in_time));
        out.emplace_back(Node(id, sign_out_time));
    }
    sort(in.begin(), in.end(), cmp1);
    sort(out.begin(), out.end(), cmp2);
    cout << in[0].id << " " << out[0].id;
    return 0;
}