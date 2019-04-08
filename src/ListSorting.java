import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Student implements Comparable<Student>{
    String id;
    String name;
    int grade;
    int sortBy;

    @Override
    public int compareTo(Student another) {
        if (sortBy == 1) {
            if (id.compareTo(another.id) < 0) {
                return -1;
            } else if (id.compareTo(another.id) > 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (sortBy == 2) {
            if (name.compareTo(another.name) < 0) {
                return -1;
            }else if (name.compareTo(another.name) > 0) {
                return 1;
            } else {
                if (id.compareTo(another.id) < 0) {
                    return -1;
                } else if (id.compareTo(another.id) > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        if (sortBy == 3) {
            if (grade < another.grade) {
                return -1;
            } else if (grade > another.grade) {
                return 1;
            } else {
                if (id.compareTo(another.id) < 0) {
                    return -1;
                } else if (id.compareTo(another.id) > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + grade;
    }
}
public class ListSorting {
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int C = Read.nextInt();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Student student = new Student();
            student.id = Read.next();
            student.name = Read.next();
            student.grade = Read.nextInt();
            student.sortBy = C;
            studentList.add(student);
        }
        Collections.sort(studentList);

        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
