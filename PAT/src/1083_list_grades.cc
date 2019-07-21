#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
    string name;
    string id;
    int grade;

    Node(string _name, string _id, int _grade) {
        name = _name;
        id = _id;
        grade = _grade;
    }
};

vector<Node> records;

bool Cmp(Node &one, Node &another) {
    return one.grade > another.grade;
}

int main() {
    int N;
    cin >> N;
    vector<Node> tmp;
    for (int i = 0; i < N; i++) {
        string name;
        string id;
        int grade;
        cin >> name >> id >> grade;
        tmp.emplace_back(Node(name, id, grade));
    }

    int min, max;
    cin >> min >> max;
    for (Node node : tmp) {
        if (node.grade <= max && node.grade >= min) {
            records.emplace_back(node);
        }
    }
    sort(records.begin(), records.end(), Cmp);

    if (records.empty()) {
        cout << "NONE";
        return 0;
    }

    for (Node node : records) {
        cout << node.name << ' ' << node.id << endl;
    }

    return 0;
}