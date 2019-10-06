#include <iostream>
#include <vector>

using namespace std;

const int kSize = 100000;

struct Node {
    int addr;
    int data;
    int next;

    Node(int _addr, int _data, int _next) {
        addr = _addr;
        data = _data;
        next = _next;
    }

    Node() {}
};

Node list[kSize];

vector<Node> Solve(int head, int K) {
    vector<Node> v1, v2, v3;
    for (int i = head; i != -1; i = list[i].next) {
        Node node = list[i];
        if (node.data < 0) {
            v1.push_back(node);
        }
        if (node.data >= 0 && node.data <= K) {
            v2.push_back(node);
        }
        if (node.data > K) {
            v3.push_back(node);
        }
    }

    for (Node node : v2) {
        v1.push_back(node);
    }
    for (Node node : v3) {
        v1.push_back(node);
    }

    return v1;
}

int main() {
    int head, N, K;
    cin >> head >> N >> K;

    for (int i = 0; i < N; ++i) {
        int addr, data, next;
        cin >> addr >> data >> next;
        list[addr] = Node(addr, data, next);
    }

    vector<Node> ans = Solve(head, K);
    for (int i = 0; i < ans.size() - 1; ++i) {
        printf("%05d %d %05d\n", ans[i].addr, ans[i].data, ans[i + 1].addr);
    }

    printf("%05d %d %d\n", ans[ans.size() - 1].addr, ans[ans.size() - 1].data, -1);
}