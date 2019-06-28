#include <iostream>
#include <vector>
#include <cstdio>

using namespace std;

const int kSize = 100000;

struct Node {
    int address;
    char data;
    int next;

    Node() {

    }

    Node(int _address, int _data, int _next) {
        address = _address;
        data = _data;
        next = _next;
    }

    bool operator==(Node &another) const {
        return address == another.address && data == another.data && next == another.next;
    }
};

Node list[kSize];

vector<Node> CreateList(int head) {
    vector<Node> ans;
    for (int i = head; i != -1; i = list[i].next) {
        ans.emplace_back(list[i]);
    }
    return ans;
}

int main() {
    int head1, head2, N;
    cin >> head1 >> head2 >> N;

    for (int i = 0; i < N; i++) {
        int address, next;
        char data;
        cin >> address >> data >> next;
        list[address] = Node(address, data, next);
    }

    vector<Node> v1 = CreateList(head1);
    vector<Node> v2 = CreateList(head2);

    int ans = -1;
    for (int i = v1.size() - 1, j = v2.size() - 1; i >= 0 && j >= 0; i--, j--) {
        if (v1[i] == v2[j]) {
            ans = v1[i].address;
        }
    }

    if (ans != -1) {
        printf("%05d", ans);
    } else {
        cout << ans;
    }
    return 0;
}