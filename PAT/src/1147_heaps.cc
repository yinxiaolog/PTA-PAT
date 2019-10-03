#include <iostream>
#include <vector>

using namespace std;

int M, N;

bool IsMaxHeap(vector<int> &list) {
    for (int i = 1; i < list.size(); ++i) {
        int father = (i - 1) / 2;
        if (list[father] < list[i]) {
            return false;
        }
    }

    return true;
}

bool IsMinHeap(vector<int> &list) {
    for (int i = 1; i < list.size(); ++i) {
        int father = (i - 1) / 2;
        if (list[father] > list[i]) {
            return false;
        }
    }

    return true;
}

void PostOrder(int root, vector<int> &list) {
    if (root >= N) {
        return;
    }

    PostOrder(root * 2 + 1, list);
    PostOrder(root * 2 + 2, list);

    if (root == 0) {
        cout << list[0];
    } else {
        cout << list[root] << ' ';
    }
}

int main() {
    cin >> M >> N;
    for (int i = 0; i < M; ++i) {
        vector<int> v(N);
        for (int j = 0; j < N; ++j) {
            cin >> v[j];
        }

        if (IsMaxHeap(v)) {
            cout << "Max Heap" << endl;
        } else if (IsMinHeap(v)) {
            cout << "Min Heap" << endl;
        } else {
            cout << "Not Heap" << endl;
        }

        PostOrder(0, v);
        cout << endl;
    }

    return 0;
}