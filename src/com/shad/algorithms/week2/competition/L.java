package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class L {
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
        int m = scanner.nextInt();
        int[] drills = new int[n];
//        int[] dub = new int[m];
        for (int i = 0; i < n; i++) {
            drills[i] = scanner.nextInt();
        }
        int cursor = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int dubSize = scanner.nextInt();
            int currentMin, nextMin;
            do {
                 currentMin = Math.abs(drills[cursor] - dubSize);
                 cursor++;
                 if (cursor == n) {
                     break;
                 }
                 nextMin = Math.abs(drills[cursor] - dubSize);
            } while (currentMin > nextMin);
            cursor--;
            if (min > currentMin) {
                min = currentMin;
            }
        }
        System.out.println(min);
    }
}
