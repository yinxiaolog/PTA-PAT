import util.Read;

import java.io.IOException;

public class Stack {
    private static java.util.Stack<Integer> stack = new java.util.Stack();
    private static int[] c = new int[100010];

    private static int lowbit(int i) {
        return i & -i;
    }

    private static void update(int x, int v) {
        for (int i = x; i < c.length; i += lowbit(i)) {
            c[i] += v;
        }
    }

    private static int sum(int x) {
        int sum = 0;
        for (int i = x; i >= 1; i -= lowbit(i)) {
            sum += c[i];
        }

        return sum;
    }

    private static int peekMedian() {
        int left = 1;
        int right = c.length;
        int mid;
        int k = (stack.size() + 1) / 2;

        while (left < right) {
            mid = (left + right) / 2;
            if (sum(mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        //c = new int[N];
        String operation;
        int key;

        for (int i = 0; i < N; i++) {
            operation = Read.next();
            if (operation.equals("Push")) {
                key = Read.nextInt();
                stack.push(key);
                update(key, 1);
            }

            if (operation.equals("Pop")) {
                if (stack.isEmpty()) {
                    System.out.println("Invalid");
                } else {
                    update(stack.peek(), -1);
                    System.out.println(stack.peek());
                    stack.pop();
                }
            }

            if (operation.equals("PeekMedian")) {
                if (stack.isEmpty()) {
                    System.out.println("Invalid");
                } else {
                    System.out.println(peekMedian());
                }
            }
        }
    }
}
