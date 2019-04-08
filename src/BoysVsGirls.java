import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoysVsGirls {
    private static class Student {
        String name;
        String gender;
        String id;
        int grade;

        public Student(String name, String gender, String id, int grade) {
            this.name = name;
            this.gender = gender;
            this.id = id;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return name + " " + id;
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        List<Student> students = new ArrayList<>();

        Student highestGradeOfFemale = new Student(null, null, "F", Integer.MIN_VALUE);
        Student lowestGradeOfMale = new Student(null, null, "M", Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            Student student = new Student(Read.next(), Read.next(), Read.next(), Read.nextInt());
            students.add(student);
        }

        for (Student student : students) {
            if ("F".equals(student.gender)) {
                if (student.grade > highestGradeOfFemale.grade) {
                    highestGradeOfFemale = student;
                }
            }

            if ("M".equals(student.gender)) {
                if (student.grade < lowestGradeOfMale.grade) {
                    lowestGradeOfMale = student;
                }
            }
        }

        if (highestGradeOfFemale.name == null) {
            System.out.println("Absent");
        } else {
            System.out.println(highestGradeOfFemale);
        }

        if (lowestGradeOfMale.name == null) {
            System.out.println("Absent");
        } else {
            System.out.println(lowestGradeOfMale);
        }

        if (highestGradeOfFemale.name == null || lowestGradeOfMale.name == null) {
            System.out.println("NA");
        } else {
            System.out.println(highestGradeOfFemale.grade - lowestGradeOfMale.grade);
        }
    }
}
