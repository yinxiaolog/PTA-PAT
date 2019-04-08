import util.Read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GeneralPalindromicNumber {
    private static List<Integer> list = new ArrayList<>();

    private static void change(int N, int b) {

        Stack<Integer> stack = new Stack<>();
        while (N != 0) {
            int remainder = N % b;
            stack.push(remainder);
            N /= b;
        }

        //System.out.println(stack.toString());
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return;
    }

    private static boolean isPalindromicNumber() {
        List<Integer> tempList = new ArrayList<>();

        tempList.addAll(list);
        if (tempList.size() == 1) {
            return true;
        }

        for (int i = tempList.size(); i > 1; ) {
            if (tempList.size() == 1) {
                break;
            }
            int first = tempList.get(0);
            int last = tempList.get(tempList.size() - 1);
            if (first != last) {
                return false;
            }
            tempList.remove(0);
            tempList.remove(tempList.size() - 1);
            tempList.toString();
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        int b = Read.nextInt();
        change(N, b);
        if (isPalindromicNumber()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        list.toString();
        System.out.print(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            System.out.print(" " + list.get(i));
        }
    }
}
