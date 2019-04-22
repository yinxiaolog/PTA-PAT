import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheWorldsRichest {
    private static class People implements Comparable<People>{
        String name;
        int age;
        int netWorth;

        @Override
        public int compareTo(People another) {
            if (netWorth < another.netWorth) {
                return 1;
            }

            if (netWorth > another.netWorth) {
                return -1;
            }

            if (age < another.age) {
                return -1;
            }

            if (age > another.age) {
                return 1;
            }

            if (name.compareTo(another.name) < 0) {
                return -1;
            }

            if (name.compareTo(another.name) > 0) {
                return 1;
            }

            return 0;
        }

        @Override
        public String toString() {
            return name + " " + age + " " + netWorth;
        }
    }

    private static List<People>[] allPeople = new List[201];

    private static List<People> peopleOfAges(int start, int end) {
        List<People> ans = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            Collections.sort(allPeople[i]);
            //System.out.println(allPeople[i]);
            for (int j = 0; j < allPeople[i].size() && j < 100; j++) {
                ans.add(allPeople[i].get(j));
            }
            //ans.addAll(allPeople[i]);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int K = Read.nextInt();

        for (int i = 0; i < allPeople.length; i++) {
            allPeople[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            People people = new People();
            people.name = Read.next();
            people.age = Read.nextInt();
            people.netWorth = Read.nextInt();
            allPeople[people.age].add(people);
        }

        for (int i = 1; i <= K; i++) {
            int M = Read.nextInt();
            int ageMin = Read.nextInt();
            int ageMax = Read.nextInt();
            List<People> peopleList = peopleOfAges(ageMin, ageMax);
            Collections.sort(peopleList);
            System.out.printf("Case #%d:\n", i);
            if (peopleList.isEmpty()) {
                System.out.println("None");
            } else {
                for (int j = 0; j < peopleList.size() && j < M; j++) {
                    System.out.println(peopleList.get(j));
                }
            }
        }
    }
}
