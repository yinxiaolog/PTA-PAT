#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

const int kSize = 1001;

int father[kSize];
int hobbies[kSize];
int is_root[kSize];

void Init(int N) {
    for (int i = 1; i <= N; i++) {
        father[i] = i;
        is_root[i] = 0;
    }
}

int FindFather(int x) {
    while (x != father[x]) {
        x = father[x];
    }

    return x;
}

void Union(int a, int b) {
    int fa = FindFather(a);
    int fb = FindFather(b);

    if (fa != fb) {
        father[fb] = fa;
    }
}

int main() {
    int N;
    cin >> N;

    Init(N);
    for (int i = 1; i <= N; i++) {
        string str;
        cin >> str;
        str = str.substr(0, str.size() - 1);
        int K = stoi(str);
        for (int j = 0; j < K; j++) {
            int hobby;
            cin >> hobby;
            if (hobbies[hobby] == 0) {
                hobbies[hobby] = i;
            }
            Union(i, FindFather(hobbies[hobby]));
        }
    }

    for (int i = 1; i <= N; i++) {
        is_root[FindFather(i)]++;
    }

    sort(is_root, is_root + kSize, greater<int>());
    int count = 0;
    for (int i = 0; i < N; i++) {
        if (is_root[i] > 0) {
            count++;
        }
    }
    cout << count << endl;

    for (int i = 0; i < count; i++) {
        if (i != count - 1) {
            cout << is_root[i] << " ";
        } else {
            cout << is_root[i];
        }
    }
    return 0;
}