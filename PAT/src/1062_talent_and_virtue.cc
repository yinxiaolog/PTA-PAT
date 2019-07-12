#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

struct People {
    int id;
    int virtue;
    int talent;
    int total;

    People(int _id, int _virtue, int _talent) {
        id = _id;
        virtue = _virtue;
        talent = _talent;
        total = _virtue + _talent;
    }
};

bool Cmp(People &one, People &another) {
    if (one.total < another.total) {
        return false;
    }

    if (one.total > another.total) {
        return true;
    }

    if (one.virtue < another.virtue) {
        return false;
    }

    if (one.virtue > another.virtue) {
        return true;
    }

    return one.id < another.id;
}

int main() {
    int N, L, H;
    vector<People> sage, nobleman, fool, rest;

    cin >> N >> L >> H;
    for (int i = 0; i < N; i++) {
        int id, virtue, talent;
        cin >> id >> virtue >> talent;
        if (virtue < L || talent < L) {
            continue;
        }
        if (virtue >= H && talent >= H) {
            sage.emplace_back(People(id, virtue, talent));
            continue;
        }

        if (virtue >= H && talent < H) {
            nobleman.emplace_back(People(id, virtue, talent));
            continue;
        }

        if (virtue < H && talent < H && virtue >= talent) {
            fool.emplace_back(People(id, virtue, talent));
            continue;
        }

        rest.emplace_back(People(id, virtue, talent));
    }

    sort(sage.begin(), sage.end(), Cmp);
    sort(nobleman.begin(), nobleman.end(), Cmp);
    sort(fool.begin(), fool.end(), Cmp);
    sort(rest.begin(), rest.end(), Cmp);

    cout << sage.size() + nobleman.size() + fool.size() + rest.size() << endl;
    for (People people : sage) {
        printf("%08d %d %d\n", people.id, people.virtue, people.talent);
    }
    for (People people : nobleman) {
        printf("%08d %d %d\n", people.id, people.virtue, people.talent);
    }
    for (People people : fool) {
        printf("%08d %d %d\n", people.id, people.virtue, people.talent);
    }
    for (People people : rest) {
        printf("%08d %d %d\n", people.id, people.virtue, people.talent);
    }
    return 0;
}