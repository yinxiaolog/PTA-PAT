import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Testee implements Comparable<Testee> {
    private String id;
    private int score;
    private int finalRank;
    private int locationNumber;
    private int localRank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFinalRank() {
        return finalRank;
    }

    public void setFinalRank(int finalRank) {
        this.finalRank = finalRank;
    }

    public int getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(int locationNumber) {
        this.locationNumber = locationNumber;
    }

    public int getLocalRank() {
        return localRank;
    }

    public void setLocalRank(int localRank) {
        this.localRank = localRank;
    }

    @Override
    public int compareTo(Testee o) {
        if (finalRank < o.finalRank) {
            return -1;
        }
        if (finalRank > o.finalRank) {
            return 1;
        }

        if (finalRank == o.finalRank) {
            if (id.compareTo(o.getId()) < 0) {
                return -1;
            }
            if (id.compareTo(o.getId()) > 0) {
                return 1;
            }
        }
        return 0;
    }
}

public class PTARanking {
    private static int N;

    private static void bucketSort(boolean isLocalRankOrFinalRank, List<Testee> testeesList) {
        List<Testee>[] bucket = new List[101];
        for (int i = 0; i < bucket.length; i++) {
            List<Testee> list = new ArrayList<>();
            bucket[i] = list;
        }

        for (Testee testee : testeesList) {
            bucket[testee.getScore()].add(testee);
        }

        int rank = 1;
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == null || bucket[i].size() == 0) {
                continue;
            } else {
                for (Testee testee : bucket[i]) {
                    if (isLocalRankOrFinalRank) {
                        testee.setFinalRank(rank);
                    } else {
                        testee.setLocalRank(rank);
                    }
                }
                rank += bucket[i].size();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        N = Read.nextInt();
        List<Testee>[] lists = new List[N];
        for (int i = 0; i < N; i++) {
            int K = Read.nextInt();
            List<Testee> list = new ArrayList<>();
            for (int j = 0; j < K; j++) {
                Testee testee = new Testee();
                testee.setId(Read.next());
                testee.setScore(Read.nextInt());
                testee.setLocationNumber(i + 1);
                list.add(testee);
            }
            lists[i] = list;
        }

        List<Testee> testeesList = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            bucketSort(false, lists[i]);
            testeesList.addAll(lists[i]);
        }

        bucketSort(true, testeesList);
        Collections.sort(testeesList);

        System.out.println(testeesList.size());
        for (Testee testee : testeesList) {
            System.out.println(testee.getId() + " " + testee.getFinalRank() + " " +
                    testee.getLocationNumber() + " " + testee.getLocalRank());
        }
    }
}