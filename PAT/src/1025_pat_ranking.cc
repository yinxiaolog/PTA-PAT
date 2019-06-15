#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Student {
    string id;
    int score;
    int final_rank;
    int room;
    int local_rank;

    Student(string _id, int _score, int _room) {
        id = _id;
        score = _score;
        room = _room;
    }
};

vector<Student> students;

bool cmp(Student studentA, Student studentB) {
    if (studentA.score == studentB.score) {
        return studentA.id < studentB.id;
    }
    return studentA.score > studentB.score;
}

int main() {
    int N;
    cin >> N;

    for (int i = 1; i <= N; i++) {
        int K = 0;
        cin >> K;
        vector<Student> tmp;
        for (int j = 1; j <= K; j++) {
            string id;
            int score;
            cin >> id >> score;
            Student student = Student(id, score, i);
            tmp.push_back(student);
        }
        sort(tmp.begin(), tmp.end(), cmp);

        int rank = 1;
        tmp[0].local_rank = rank;
        for (int j = 1; j < tmp.size(); j++) {
            if (tmp[j].score == tmp[j - 1].score) {
                tmp[j].local_rank = rank;
            } else {
                rank = j + 1;
                tmp[j].local_rank = rank;
            }
        }

        students.insert(students.end(), tmp.begin(), tmp.end());
    }

    sort(students.begin(), students.end(), cmp);

    int rank = 1;
    students[0].final_rank = rank;
    for (int i = 1; i < students.size(); i++) {
        if (students[i].score == students[i - 1].score) {
            students[i].final_rank = rank;
        } else {
            rank = i + 1;
            students[i].final_rank = rank;
        }
    }

    cout << students.size() << endl;
    for (vector<Student>::iterator it = students.begin(); it != students.end(); it++) {
        Student student = *it;
        cout << student.id << " " << student.final_rank << " " << student.room << " " << student.local_rank << endl;
    }

    return 0;
}