import util.Read;

import java.io.IOException;

public class ListGrades {
    private static class Student {
        String name;
        String id;
        int grade;

        public Student(String name, String id, int grade) {
            this.name = name;
            this.id = id;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return name + " " + id;
        }
    }

    private static Student[] students = new Student[101];

    public static void main(String[] args) throws IOException {
        Read.init();

        int N = Read.nextInt();
        for (int i = 0; i < N; i++) {
            Student student = new Student(Read.next(), Read.next(), Read.nextInt());
            students[student.grade] = student;
        }

        int grade1 = Read.nextInt();
        int grade2 = Read.nextInt();

        boolean flag = false;
        for (int i = grade2; i >= grade1; i--) {
            if (students[i] != null) {
                flag = true;
                System.out.println(students[i]);
            }
        }

        if (!flag) {
            System.out.println("NONE");
        }
    }
}
