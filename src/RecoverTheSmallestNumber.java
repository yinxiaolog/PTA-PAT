import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class RecoverTheSmallestNumber {
    public static void main(String[] args) throws IOException {
        Read.init();
        int N = Read.nextInt();
        String[] numbers = new String[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Read.next();
        }
        Arrays.sort(numbers);

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < N; i++) {

            if (i == numbers.length - 1) {
                ans.append(numbers[i]);
                break;
            }
            if (numbers[i].charAt(0) == numbers[i + 1].charAt(0)) {
                if ((numbers[i] + numbers[i + 1]).compareTo(numbers[i + 1] + numbers[i]) < 0) {
                    ans.append(numbers[i]);
                } else {
                    ans.append(numbers[i + 1]);
                    String tmp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = tmp;
                }
            } else {
                ans.append(numbers[i]);
            }
        }

        if (ans.toString().charAt(ans.length() - 1) == '0') {
            System.out.println(0);
            return;
        }
        while (ans.toString().charAt(0) == '0') {
            ans = ans.replace(0,1,"");
            if (ans.toString().charAt(0) != '0') {
                break;
            }
        }

        System.out.println(ans);
    }
}
