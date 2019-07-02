#include <iostream>
#include <vector>
#include <string>

using namespace std;

struct Student {
    string name;
    char gender;
    string id;
    int grade;

    Student(string _name, char _gender, string _id, int _grade) {
        name = _name;
        gender = _gender;
        id = _id;
        grade = _grade;
    }
};

int main() {
    int N;
    cin >> N;
    vector<Student> students;
    for (int i = 0; i < N; i++) {
        string name;
        char gender;
        string id;
        int grade;
        cin >> name >> gender >> id >> grade;
        students.emplace_back(Student(name, gender, id, grade));
    }

    Student female = Student("", 'F', "", -1);
    Student male = Student("", 'M', "", 101);

    for (Student &student : students) {
        if (student.gender == 'M' && student.grade < male.grade) {
            male = student;
        }

        if (student.gender == 'F' && student.grade > female.grade) {
            female = student;
        }
    }
    if (female.grade == -1) {
        cout << "Absent" << endl;
    } else {
        cout << female.name << " " << female.id << endl;
    }

    if (male.grade == 101) {
        cout << "Absent" << endl;
    } else {
        cout << male.name << " " << male.id << endl;
    }

    if (female.grade == -1 || male.grade == 101) {
        cout << "NA" << endl;
    } else {
        cout << female.grade - male.grade << endl;
    }
    return 0;
}