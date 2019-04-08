package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Read {
    static BufferedReader reader;
    static StringTokenizer tokenizer;
    static Scanner sc = new Scanner(System.in);

    public static void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        tokenizer = new StringTokenizer("");
    }

    public static String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(
                    reader.readLine());
        }
        return tokenizer.nextToken();
    }

    public static String nextLine() throws IOException {
        return sc.nextLine();
    }
    public static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public static long nextLong() throws IOException {
        return Long.parseLong(next());
    }
}
