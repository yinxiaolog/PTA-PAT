#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, K;
    cin >> N >> K;
    unordered_map<string, vector<int> > hash_map;

    for (int i = 0; i < K; i++) {
        int course, students;
        cin >> course >> students;
        for (int j = 0; j < students; j++) {
            string name;
            cin >> name;
            hash_map[name].push_back(course);
        }
    }

    for (int i = 0; i < N; i++) {
        string name;
        cin >> name;
        if (hash_map.find(name) == hash_map.end()) {
            cout << name << " " << 0 << endl;
        } else {
            vector<int> &v = hash_map[name];
            sort(v.begin(), v.end());
            cout << name << " " << v.size();
            for (int x : v) {
                cout << " " << x;
            }
            cout << endl;
        }
    }

    return 0;
}