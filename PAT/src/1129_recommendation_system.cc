#include <iostream>
#include <set>

using namespace std;

struct Node {
    int cnt;
    int val;

    Node(int _val, int _cnt) {
        cnt = _cnt;
        val = _val;
    }

    bool operator<(const Node &another) const {
        return cnt == another.cnt ? val < another.val : cnt > another.cnt;
    }
};

int nums[50001] = {0};

set<Node>::iterator Find(int key, set<Node> &s) {
    for (auto it = s.begin(); it != s.end(); ++it) {
        if (it->val == key) {
            return it;
        }
    }

    return s.end();
}

int main() {
    int N, K;
    cin >> N >> K;

    set<Node> s;
    int item;
    cin >> item;
    nums[item]++;
    s.insert(Node(item, 1));
    for (int i = 1; i < N; ++i) {
        cin >> item;
        int k = 0;
        cout << item << ':';
        for (auto it = s.begin(); it != s.end() && k < K; ++it) {
            cout << ' ' << it->val;
            k++;
        }
        cout << endl;
        auto it = s.find(Node(item, nums[item]));
        nums[item]++;
        if (it == s.end()) {
            s.insert(Node(item, nums[item]));
        } else {
            s.erase(it);
            s.insert(Node(item, nums[item]));
        }
    }

    return 0;
}