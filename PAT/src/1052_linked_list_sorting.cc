#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int kSize = 100000;

struct Node {
    int address;
    int key;
    int next;

    Node() {
        address = key = next = -1;
    }

    Node(int _address, int _key, int _next) {
        address = _address;
        key = _key;
        next = _next;
    }
};

Node arr[kSize];

bool Cmp(Node &A, Node &B) {
    return A.key < B.key;
}

int main() {
    int N;
    int head;
    vector<Node> list;
    cin >> N >> head;


    for (int i = 0; i < N; i++) {
        int address, key, next;
        cin >> address >> key >> next;
        arr[address] = Node(address, key, next);
    }

    for (int i = head; i != -1; i = arr[i].next) {
        list.push_back(arr[i]);
    }

    sort(list.begin(), list.end(), Cmp);

    if (list.size() == 0) {
        printf("%d %d\n", (int) list.size(), -1);
        return 0;
    }

    printf("%d %05d\n", (int) list.size(), list[0].address);

    for (int i = 0; i < list.size(); i++) {
        if (i != list.size() - 1) {
            printf("%05d %d %05d\n", list[i].address, list[i].key, list[i + 1].address);
        } else {
            printf("%05d %d %d", list[i].address, list[i].key, -1);
        }
    }
    return 0;
}