import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TalentAndVirtue {
    private static class People implements Comparable<People> {
        String id;
        int virtue;
        int talent;

        public People(String id, int virtue, int talent) {
            this.id = id;
            this.virtue = virtue;
            this.talent = talent;
        }

        @Override
        public int compareTo(People another) {
            if (virtue + talent < another.virtue + another.talent) {
                return 1;
            } else if (virtue + talent > another.virtue + another.talent) {
                return -1;
            } else {
                if (virtue < another.virtue) {
                    return 1;
                } else if (virtue > another.virtue) {
                    return -1;
                } else {
                    return id.compareTo(another.id);
                }
            }
        }

        @Override
        public String toString() {
            return id + " " + virtue + " " + talent;
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int L = Read.nextInt();
        int H = Read.nextInt();

        List<People> sages = new ArrayList<>();
        List<People> noblemen = new ArrayList<>();
        List<People> fooMen = new ArrayList<>();
        List<People> rest = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String id = Read.next();
            int virtue = Read.nextInt();
            int talent = Read.nextInt();
            if (virtue < L || talent < L) {
                continue;
            }
            if (virtue >= H && talent >= H) {
                new People(id, virtue, talent);
                sages.add(new People(id, virtue, talent));
                continue;
            }

            if (virtue >= H && talent < H) {
                noblemen.add(new People(id, virtue, talent));
                continue;
            }

            if (virtue < H && talent < H && virtue >= talent) {
                fooMen.add(new People(id, virtue, talent));
                continue;
            }

            rest.add(new People(id, virtue, talent));
        }

        Collections.sort(sages);
        Collections.sort(noblemen);
        Collections.sort(fooMen);
        Collections.sort(rest);

        System.out.println(sages.size() + noblemen.size() + fooMen.size() + rest.size());
        for (People people : sages) {
            System.out.println(people);
        }

        for (People people : noblemen) {
            System.out.println(people);
        }

        for (People people : fooMen) {
            System.out.println(people);
        }

        for (People people : rest) {
            System.out.println(people);
        }
    }
}
