#include <iostream>
#include <cstdio>
#include <unordered_set>
#include <vector>

using namespace std;

const int kSize = 100000;

struct Node {
    int addr;
    int key;
    int next;

    Node() {
        addr = key = next = -1;
    }

    Node(int _addr, int _key, int _next) {
        addr = _addr;
        key = _key;
        next = _next;
    }
};

Node list[kSize];
vector<Node> no_repeat, repeat;

void Solve(int head) {
    unordered_set<int> keys;
    for (int i = head; i != -1; i = list[i].next) {
        Node &node = list[i];
        int tmp_size = keys.size();
        keys.insert(abs(node.key));
        if (tmp_size != keys.size()) {
            no_repeat.push_back(node);
        } else {
            repeat.push_back(node);
        }
    }
}

void PrintList(vector<Node> &list) {
    if (list.size() == 0) {
        return;
    }

    for (int i = 0; i < list.size() - 1; i++) {
        printf("%05d %d %05d\n", list[i].addr, list[i].key, list[i + 1].addr);
    }

    printf("%05d %d %d\n", list[list.size() - 1].addr, list[list.size() - 1].key, -1);
}

int main() {
    int head, N;
    cin >> head >> N;
    for (int i = 0; i < N; i++) {
        int addr, key, next;
        cin >> addr >> key >> next;
        list[addr] = Node(addr, key, next);
    }

    Solve(head);
    PrintList(no_repeat);
    PrintList(repeat);

    return 0;
}