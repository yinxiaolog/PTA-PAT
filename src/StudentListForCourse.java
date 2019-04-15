import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentListForCourse {
    private static String[] names;
    private static List<Integer>[] course;
    public static void main(String[] args) throws IOException {
        Read.init();

        int N = Read.nextInt();
        int K = Read.nextInt();

        names = new String[N];
        course = new List[K];

        for (int i = 0; i < K; i++) {
            course[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            names[i] = Read.next();
            int C = Read.nextInt();
            for (int j = 0; j < C; j++) {
                course[Read.nextInt() - 1].add(i);
            }
        }


        List<String> students = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            List<Integer> list = course[i];

            students.clear();
            for (Integer j : list) {
                students.add(names[j]);
            }
            Collections.sort(students);
            System.out.println(i + 1 + " " + list.size());
            for (String s : students) {
                System.out.println(s);
            }
        }
    }
}
