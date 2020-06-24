package com.shad.algorithms.week4.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {
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

    private static long[] answers;

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        answers = new long[n+1];
        answers[0] = 0;
        answers[1] = 1;
        if (n >= 2) {
            answers[2] = 1;
        }
        for (int i = 3; i <= n; i++) {
            answers[i] = -1;
        }
        System.out.println(getAnswer(n, k));
    }

    public static long getAnswer(int position, int k) {
        if (answers[position] != -1) {
            return answers[position];
        } else {
            int cursor = position - 1;
            long variants = 0;
            while (cursor >= 1 && position - cursor <= k) {
                variants += getAnswer(cursor, k);
                cursor--;
            }
            answers[position] = variants;
            return variants;
        }
    }
}
