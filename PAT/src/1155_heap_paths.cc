#include <iostream>
#include <vector>

using namespace std;

const int kSize = 1001;

int heap[kSize];
int N;
vector<int> path;

void Dfs(int root) {
    path.push_back(heap[root]);
    if (root * 2 + 1 > N) {
        for (int i = 0; i < path.size(); ++i) {
            if (i == 0) {
                cout << path[i];
            } else {
                cout << ' ' << path[i];
            }
        }
        cout << endl;
    }

    if (root * 2 + 2 < N) {
        Dfs(root * 2 + 2);
    }

    if (root * 2 + 1 < N) {
        Dfs(root * 2 + 1);
    }

    path.pop_back();
}

bool isMaxHeap() {
    for (int i = 1; i < N; ++i) {
        int father = (i - 1) / 2;
        if (heap[i] > heap[father]) {
            return false;
        }
    }

    return true;
}

bool isMinHeap() {
    for (int i = 0; i < N; ++i) {
        int father = (i - 1) / 2;
        if (heap[i] < heap[father]) {
            return false;
        }
    }

    return true;
}

int main() {
    cin >> N;
    for (int i = 0; i < N; ++i) {
        cin >> heap[i];
    }

    Dfs(0);
    if (isMaxHeap()) {
        cout << "Max Heap\n";
    } else if (isMinHeap()) {
        cout << "Min Heap\n";
    } else {
        cout << "Not Heap\n";
    }
    return 0;
}