import util.Read;

import java.io.IOException;

public class MainProductOfPolynomials {
    private static double[] p1 = new double[1001];
    private static double[] p2 = new double[1001];
    private static double[] ans = new double[2001];

    private static void sumOfTwoPolynomials(){
        for(int i = 0; i < p1.length;i++){
            for(int j = 0; j < p2.length; j++){
                ans[i + j] += p1[i] * p2[j];
            }
        }
    }
    public static void main(String[] args) throws IOException {

        //p1[1] = 2.4;
        //p1[0] = 6;
        //p1[3] = 5;
        //p1[2] = 4;
        //p2[2] = 2;
        //p2[1] = 1;
        Read.init();
        int K = Read.nextInt();
        for(int i = 0; i < K;i++){
            p1[Read.nextInt()] = Read.nextDouble();
        }
        K = Read.nextInt();
        for(int i = 0; i < K;i++){
            p2[Read.nextInt()] = Read.nextDouble();
        }
        sumOfTwoPolynomials();
        int ansK = 0;
        for(int i = 0; i < ans.length;i++){
            if(ans[i] != 0){
                ansK++;
            }
        }
        System.out.print(ansK);

        for(int i = ans.length - 1; i > -1;i--){
            if(ans[i] != 0) {
                System.out.format(" %d %.1f",i, ans[i]);
            }
        }
        //System.out.println(Arrays.toString(ans));
    }
}
