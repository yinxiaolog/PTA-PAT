import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PATJudge {
    private static int[] problem;

    private static class Student implements Comparable<Student> {
        int userId;
        int[] problemId;
        int total;
        int passNum;
        int rank;

        public Student(int userId, int problems) {
            this.userId = userId;
            problemId = new int[problems];
            for (int i = 0; i < problems; i++) {
                problemId[i] = -2;
            }
        }

        public void setTotal() {
            int sum = 0;
            for (int i = 0; i < problemId.length; i++) {
                if (problemId[i] < 0) {
                    sum += 0;
                } else {
                    sum += problemId[i];
                }
            }
            total = sum;
        }

        public void setPassNum() {
            int count = 0;
            for (int i = 0; i < problemId.length; i++) {
                if (problemId[i] == problem[i]) {
                    count++;
                }
            }
            passNum = count;
        }

        public boolean isShown() {
            boolean flag = false;
            for (int i = 0; i < problemId.length; i++) {
                if (problemId[i] != -2) {
                    flag = true;
                    break;
                }
            }

            return flag;
        }

        @Override
        public int compareTo(Student another) {
            if (total < another.total) {
                return 1;
            }
            if (total > another.total) {
                return -1;
            }

            if (passNum < another.passNum) {
                return 1;
            }
            if (passNum > another.passNum) {
                return -1;
            }

            if (userId < another.userId) {
                return -1;
            }
            if (userId > another.userId) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            String id = String.format("%05d", userId);
            sb.append(id).append(" ");
            sb.append(total).append(" ");
            for (int i = 0; i < problemId.length; i++) {
                if (problemId[i] >= 0) {
                    sb.append(problemId[i]).append(" ");
                }

                if (problemId[i] == -1) {
                    sb.append("0 ");
                }

                if (problemId[i] == -2) {
                    sb.append("- ");
                }
            }

            return rank + " " + sb.toString().trim();
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int K = Read.nextInt();
        int M = Read.nextInt();
        //System.out.println(N + " " + K + " " + M);
        problem = new int[K];
        for (int i = 0; i < K; i++) {
            problem[i] = Read.nextInt();
        }

        //System.out.println(Arrays.toString(problem));

        Map<Integer, Student> studentMap = new HashMap<>(N);
        for (int i = 0; i < M; i++) {
            System.out.println(i);
            int userId = Read.nextInt();
            int problemId = Read.nextInt() - 1;
            int score = Read.nextInt();

            //System.out.println(userId + " " + problemId + " " + score);

            Student tmpStudnet = studentMap.get(userId);
            if (tmpStudnet == null) {
                Student student = new Student(userId, K);
                int tmpScore = student.problemId[problemId];
                if (score > tmpScore && score >= 0) {
                    student.problemId[problemId] = score;
                }

                if (score == -1 && tmpScore == -2) {
                    student.problemId[problemId] = 0;
                }

                studentMap.put(userId, student);
            } else {
                int tmpScore = tmpStudnet.problemId[problemId];
                if (score > tmpScore && score >= 0) {
                    tmpStudnet.problemId[problemId] = score;
                }

                if (score == -1 && tmpScore == -2) {
                    tmpStudnet.problemId[problemId] = 0;
                }
            }
        }

        List<Student> studentList = new ArrayList<>();

        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            if (entry.getValue().isShown()) {
                entry.getValue().setTotal();
                if (entry.getValue().total > 0) {
                    entry.getValue().setPassNum();
                    studentList.add(entry.getValue());
                }
            }
        }

        Collections.sort(studentList);

        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).rank = i + 1;
            if (i != 0 && studentList.get(i).total == studentList.get(i - 1).total) {
                studentList.get(i).rank = studentList.get(i - 1).rank;
            }
        }
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
