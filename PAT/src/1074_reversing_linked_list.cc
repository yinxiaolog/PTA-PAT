#include <iostream>
#include <vector>

using namespace std;

const int kSize = 100000;

struct Node {
    int addr;
    int data;
    int next;

    Node() {
        addr = data = next = -1;
    }

    Node(int _addr, int _data, int _next) {
        addr = _addr;
        data = _data;
        next = _next;
    }
};

Node list[kSize];

vector<Node> Reverse(int head, int K) {
    vector<Node> ans, tmp;
    for (int i = head; i != -1; i = list[i].next) {
        tmp.push_back(list[i]);
    }

    int i = K;
    for (; i <= tmp.size(); i += K) {
        for (int j = 0, index = i - 1; j < K; j++, index--) {
            ans.push_back(tmp[index]);
        }
    }

    if (tmp.size() % K != 0) {
        for (i -= K; i < tmp.size(); i++) {
            ans.push_back(tmp[i]);
        }
    }

    return ans;
}

int main() {
    int head, L, K;
    cin >> head >> L >> K;

    for (int i = 0; i < L; i++) {
        int addr, data, next;
        cin >> addr >> data >> next;
        list[addr] = Node(addr, data, next);
    }

    vector<Node> ans = Reverse(head, K);

    for (int i = 0; i < ans.size(); i++) {
        if (i != ans.size() - 1) {
            printf("%05d %d %05d\n", ans[i].addr, ans[i].data, ans[i + 1].addr);
        } else {
            printf("%05d %d %d\n", ans[i].addr, ans[i].data, -1);
        }
    }
    return 0;
}