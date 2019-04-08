import util.Read;

import java.io.IOException;
import java.util.Arrays;

public class MainTheBestRank {
    private static int K;
    private static int[] nums;
    private static double[][] odds = new double[3][3];
    private static int[][] records;//= {{310101, 98, 85, 88, 0}, {310102, 70, 95, 88, 0}, {310103, 82, 87, 94, 0}, {310104, 91, 91, 91, 0}};
    private static int[][] ans;//= new int[4][5];

    private static void average() {
        for (int i = 0; i < records.length; i++) {
            int average = (int) Math.round((records[i][1] + records[i][2] + records[i][3]) / 3.0);
            records[i][4] = average;
        }
    }

    private static int[] exchange(int[] a) {
        int[] temp = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            temp[a.length - 1 - i] = a[i];
        }
        return temp;
    }

    private static void sort() {
        average();
        int[] temp = new int[records.length];
        for (int i = 0; i < records.length; i++) {
            temp[i] = records[i][4];
        }
        Arrays.sort(temp);
        temp = exchange(temp);
        //System.out.println(Arrays.toString(temp));
        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (records[i][4] == temp[j]) {
                    ans[i][1] = j + 1;
                    break;
                }
            }
        }

        for (int i = 0; i < records.length; i++) {
            temp[i] = records[i][1];
        }
        Arrays.sort(temp);
        temp = exchange(temp);
        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (records[i][1] == temp[j]) {
                    ans[i][2] = j + 1;
                    break;
                }
            }
        }

        for (int i = 0; i < records.length; i++) {
            temp[i] = records[i][2];
        }
        Arrays.sort(temp);
        temp = exchange(temp);
        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (records[i][2] == temp[j]) {
                    ans[i][3] = j + 1;
                    break;
                }
            }
        }

        for (int i = 0; i < records.length; i++) {
            temp[i] = records[i][3];
        }
        Arrays.sort(temp);
        temp = exchange(temp);
        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (records[i][3] == temp[j]) {
                    ans[i][4] = j + 1;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Read.init();
        int M = Read.nextInt();
        int N = Read.nextInt();
        records = new int[M][5];
        ans = new int[M][5];
        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < records[0].length - 1; j++) {
                records[i][j] = Read.nextInt();
            }
        }
        for (int i = 0; i < records.length; i++) {
            ans[i][0] = records[i][0];
        }
        sort();

        String s = "ACME";
        int[] student = new int[N];
        for(int i = 0; i < student.length;i++){
            student[i] = Read.nextInt();
        }

        for (int i = 0; i < student.length; i++) {
            for (int j = 0; j < ans.length; j++) {
                if (student[i] == ans[j][0]) {
                    int min = Integer.MAX_VALUE;
                    int index = -1;
                    for (int k = 1; k < ans[0].length; k++) {
                        if (ans[j][k] < min) {
                            min = ans[j][k];
                            index = k;
                        }
                    }
                    System.out.println(min + " " + s.charAt(index - 1));
                    break;
                }
                if (j == ans.length - 1 && student[i] != ans[j][0]) {
                    System.out.println("N/A");
                }
            }
        }
    }
}
