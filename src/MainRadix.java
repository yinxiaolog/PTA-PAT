import util.Read;

import java.io.IOException;

public class MainRadix {
    private static int K;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        Read.init();
        String s1 = Read.next();
        String s2 = Read.next();
        int tag = Read.nextInt();
        long radix = Read.nextLong();
        long N1 = 0;
        if (tag == 1) {
            N1 = convert(s1, radix);
            long ans = radix(N1, s2);
            if (ans == -1) {
                System.out.println("Impossible");
            } else {
                System.out.println(ans);
            }
        }

        long N2 = 0;
        if (tag == 2) {
            N2 = convert(s2, radix);
            long ans = radix(N2, s1);
            if (ans == -1) {
                System.out.println("Impossible");
            } else {
                System.out.println(ans);
            }
        }
    }

    public static long radix(long N1, String N2) {
        long low = 2;
        long high = Long.MAX_VALUE;
        if(N2.length()==1){
            if(N1 == number(N2.charAt(0))){
                return number(N2.charAt(0)) + 1;
            }
        }

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long temp = convert(N2, mid);
            //System.out.println("temp: " + temp);
            if (N1 < temp) {
                high = mid - 1;
            } else if (N1 > temp) {
                low = mid + 1;
            } else {
                return mid;
            }
            //System.out.println(mid);
        }
        return -1;
    }

    public static long convert(String digits, long radix) {
        int length = digits.length() - 1;
        long sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            int number = number(digits.charAt(i));
            if (number >= radix) {
                return -1;
            }
            sum += number * Math.pow((double) radix, (double) (length - i));
        }
        return sum;
    }

    private static int number(char c) {
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int start = 0;
        int end = chars.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (c < chars[mid]) {
                end = mid - 1;
            } else if (c > chars[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int elevator() {
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] > 0) {
                sum += Math.abs(nums[i + 1] - nums[i]) * 6;
            } else {
                sum += Math.abs(nums[i + 1] - nums[i]) * 4;
            }
        }
        sum += (nums.length - 1) * 5;
        return sum;
    }

    public static int[] maximumSubsequenceSum() {
        int[] ans = new int[3];
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                flag = true;
            }
        }
        if (!flag) {
            ans[0] = 0;
            ans[1] = nums[0];
            ans[2] = nums[nums.length - 1];
            return ans;
        }

        int start = 0;
        int end = nums.length - 1;
        int index = 0;
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum < 0) {
                sum = 0;
                index = i + 1;
            } else if (sum > maxSum) {
                maxSum = sum;
                start = index;
                end = i;
            }
        }

        if (maxSum < 0) {
            maxSum = 0;
        }
        ans[0] = maxSum;
        ans[1] = start;
        ans[2] = end;
        return ans;
    }
}