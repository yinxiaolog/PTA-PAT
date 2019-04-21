import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PopSequence {
    private static  int M;
    private static  int N;

    private static void initStack(Stack<Integer> stack) {
        for (int i = N; i >=1; i--) {
            stack.push(i);
        }
        return;
    }
    private static boolean isPopSequence(List<Integer> sequence) {
        Stack<Integer> helper = new Stack<>();
        initStack(helper);
        Stack<Integer> stack = new Stack<>();
        if (sequence.get(0) > M) {
            return false;
        }
        while (sequence.size() > 0){
            int i = sequence.get(0);
            if (stack.isEmpty() || stack.peek() < i) {
                while (!helper.isEmpty() && helper.peek() <= i) {
                    stack.push(helper.pop());
                }

                if (stack.size() > M) {
                    return false;
                }
            }

            sequence.remove(0);
            if (stack.pop() != i) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException{
        Read.init();
        M = Read.nextInt();
        N = Read.nextInt();

        int K = Read.nextInt();
        for (int i = 0; i < K; i++) {
            List<Integer> sequence = new ArrayList<>();
            for (int j = 0; j < N; j ++) {
                sequence.add(Read.nextInt());
            }
            if (isPopSequence(sequence)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
