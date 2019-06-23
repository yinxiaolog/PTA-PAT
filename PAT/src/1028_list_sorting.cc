#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

struct Node {
    int id;
    string name;
    int grade;

    Node(int _id, string _name, int _grade) {
        id = _id;
        name = _name;
        grade = _grade;
    }
};

vector<Node> students;

bool CmpById(Node &a, Node &b) {
    return a.id < b.id;
}

bool CmpByName(Node &a, Node &b) {
    if (a.name == b.name) {
        return a.id < b.id;
    }
    return a.name < b.name;
}

bool CmpByGrade(Node &a, Node &b) {
    if (a.grade == b.grade) {
        return a.id < b.id;
    }
    return a.grade < b.grade;
}

int main() {
    int N, C;
    cin >> N >> C;

    for (int i = 0; i < N; i++) {
        char str[10];
        int id;
        string name;
        int grade;
        scanf("%d %s %d", &id, str, &grade);
        name = str;
        students.emplace_back(Node(id, name, grade));
    }
    if (C == 1) {
        sort(students.begin(), students.end(), CmpById);
    }
    if (C == 2) {
        sort(students.begin(), students.end(), CmpByName);
    }
    if (C == 3) {
        sort(students.begin(), students.end(), CmpByGrade);
    }

    for (Node &node : students) {
        printf("%06d ", node.id);
        cout << node.name << " " << node.grade << endl;
    }
    return 0;
}