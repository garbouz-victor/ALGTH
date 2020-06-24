package com.shad.algorithms.week4.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D {
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

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            b[i] = scanner.nextInt();
            c[i] = scanner.nextInt();
        }
        int[] answers = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                answers[i] = a[i];
            } else {
                answers[i] = answers[i-1] + a[i];
                if (i - 3 >= 0) {
                    answers[i] = Math.min(answers[i-3] + c[i-2], answers[i]);
                }
                if (i == 2) {
                    answers[i] = Math.min(c[i-2], answers[i]);
                }
                if (i - 2 >= 0) {
                    answers[i] = Math.min(answers[i-2] + b[i-1], answers[i]);
                }
                if (i == 1) {
                    answers[i] = Math.min(b[i-1], answers[i]);
                }
            }
        }
        System.out.println(answers[n-1]);
    }
}
