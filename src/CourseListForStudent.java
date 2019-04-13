import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseListForStudent {
    private static int hash(String name) {
        int id = 0;
        for (int i = 0; i < 3; i++)
            id = 26 * id + (name.charAt(i) - 'A');
        id = id * 10 + (name.charAt(3) - '0');
        return id;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int K = Read.nextInt();
        List<Integer>[] arrays = new List[26 * 26 * 26 * 10 + 10];
        for (int i = 0; i < K; i++) {
            int Ki = Read.nextInt();
            int Ni = Read.nextInt();

            for (int j = 0; j < Ni; j++) {
                String name = Read.next();
                int hashCode = hash(name);
                if (arrays[hashCode] == null) {
                    List<Integer> list = new ArrayList<>();
                    list.add(Ki);
                    arrays[hashCode] = list;
                } else {
                    arrays[hashCode].add(Ki);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            StringBuilder name = new StringBuilder();
            name.append(Read.next());
            int hashCode = hash(name.toString());
            if (arrays[hashCode] == null) {
                System.out.println(name.toString() + " " + 0);
            } else {
                Collections.sort(arrays[hashCode]);
                System.out.print(name.toString() + " " + arrays[hashCode].size());
                for (Integer j : arrays[hashCode]) {
                    System.out.print(" " + j);
                }
                System.out.println();
            }
        }

    }
}
