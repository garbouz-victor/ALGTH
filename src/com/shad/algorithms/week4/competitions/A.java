package com.shad.algorithms.week4.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
    static class FastScanner
    {
        BufferedReader br;
        StringTokenizer stok;

        FastScanner (InputStream is)
        {
            br = new BufferedReader(new InputStreamReader(is));
        }

        String nextToken() throws IOException {
            while (stok == null || !stok.hasMoreTokens()) {
                String s = br.readLine();
                if (s == null) {
                    return null;
                }
                stok = new StringTokenizer(s);
            }
            return stok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(nextToken());
        }

        long nextLong() throws IOException {
            return Long.parseLong(nextToken());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(nextToken());
        }

        char nextChar() throws IOException {
            return (char) (br.read());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }
    }

    private static int MARKER = -11111;

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n+1];
        int[] answers = new int[n+1];
        array[0] = 0;
        answers[0] = 0;
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
            answers[i] = MARKER;
        }
        System.out.println(sum(array, answers, n));
    }

    public static int sum(int[] values, int[] answers, int stepNum) {
        if (answers[stepNum] != MARKER) {
            return answers[stepNum];
        }
        if (stepNum <= 1) {
            return values[stepNum];
        } else {
            int sum1 = sum(values, answers, stepNum - 1) + values[stepNum];
            int sum2 = sum(values, answers,stepNum - 2) + values[stepNum];
            if (sum1 > sum2) {
                answers[stepNum] = sum1;
                return sum1;
            } else {
                answers[stepNum] = sum2;
                return sum2;
            }
        }
    }
}
