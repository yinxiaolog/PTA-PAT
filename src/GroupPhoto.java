import util.Read;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GroupPhoto {
    private static class People implements Comparable<People>{
        String name;
        int height;

        public People (String name, int height) {
            this.name = name;
            this.height = height;
        }

        @Override
        public int compareTo(People another) {
            if (height < another.height) {
                return 1;
            }

            if (height > another.height) {
                return -1;
            }

            if (name.compareTo(another.name) < 0) {
                return -1;
            }

            if (name.compareTo(another.name) > 0) {
                return 1;
            }

            return 0;
        }
    }

    private static Queue<People> queue = new PriorityQueue<>();

    private static String getRow(int rowNum) {
        StringBuilder sb = new StringBuilder();
        Deque<People> deque = new LinkedList<>();
        deque.add(queue.poll());
        boolean flag = false;

        for (int i = 0; i < rowNum - 1; i++) {
            if (!flag) {
                deque.addFirst(queue.poll());
                flag = true;
                continue;
            }

            if (flag) {
                deque.addLast(queue.poll());
                flag = false;
                continue;
            }
        }

        for (People people : deque) {
            sb.append(people.name).append(":" + people.height).append(' ');
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int K = Read.nextInt();
        for (int i = 0; i < N; i++) {
            String name = Read.next();
            int height = Read.nextInt();
            queue.add(new People(name, height));
        }

        int sumRow = 0;
        int lastRow = 0;
        if (N % K == 0) {
            sumRow = N / K;
        } else {
            sumRow = N / K;
            lastRow = N - (sumRow - 1) * K;
        }

        if (lastRow != 0) {
            System.out.println(getRow(lastRow));
            for (int i = 0; i < sumRow - 1; i++) {
                System.out.println(getRow(K));
            }
        } else {
            for (int i = 0; i < sumRow; i++) {
                System.out.println(getRow(K));
            }
        }
    }
}
