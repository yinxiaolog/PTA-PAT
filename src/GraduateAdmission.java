import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GraduateAdmission {
    private static class Applicant implements Comparable<Applicant>{
        int id;
        int GE;
        int GI;
        int total;
        int rank = Integer.MAX_VALUE;
        List<Integer> schools = new ArrayList<>();

        @Override
        public int compareTo(Applicant another) {
            if(total < another.total) {
                return 1;
            }
            if (total > another.total) {
                return -1;
            }

            if (GE < another.GE) {
                return 1;
            }
            if (GE > another.GE) {
                return -1;
            }

            return 0;
        }
    }

    private static class School {
        int quota;
        int lastRank;
        List<Integer> applicants = new ArrayList<>();

        public School(int quota) {
            this.quota = quota;
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int M = Read.nextInt();
        int K = Read.nextInt();

        School[] schools = new School[M];
        for (int i = 0; i < M; i++) {
            schools[i] = new School(Read.nextInt());
        }

        Applicant[] applicants = new Applicant[N];
        for (int i = 0; i < N; i++) {
            Applicant applicant = new Applicant();
            int GE = Read.nextInt();
            int GI = Read.nextInt();
            applicant.id = i;
            applicant.GE = GE;
            applicant.GI = GI;
            applicant.total = GE + GI;
            for (int j = 0; j < K; j++) {
                applicant.schools.add(Read.nextInt());
            }
            applicants[i] = applicant;
        }

        Arrays.sort(applicants);
        for (int i = 0; i < N; i++) {
            applicants[i].rank = i + 1;
            if (i != 0 && applicants[i].total == applicants[i - 1].total && applicants[i].GE == applicants[i - 1].GE) {
                applicants[i].rank = applicants[i - 1].rank;
            }
        }

        for (Applicant applicant : applicants) {
            List<Integer> list = applicant.schools;
            for (Integer i : list) {
                if (schools[i].quota > 0) {
                    schools[i].quota--;
                    schools[i].applicants.add(applicant.id);
                    schools[i].lastRank = applicant.rank;
                    break;
                } else {
                    if (applicant.rank == schools[i].lastRank) {
                        schools[i].quota--;
                        schools[i].applicants.add(applicant.id);
                    }
                }
            }
        }

        for (School school : schools) {
            if (school.applicants.size() == 0) {
                System.out.println();
            } else {
                Collections.sort(school.applicants);
                System.out.println(school.applicants.toString().replaceAll("[\\[\\],]", ""));
            }
        }
    }
}
