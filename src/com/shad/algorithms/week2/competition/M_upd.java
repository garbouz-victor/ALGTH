package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class M_upd {
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
        int[] kens = new int[n];
        for (int i = 0; i < n; i++) {
            kens[i] = scanner.nextInt();
        }
        // shuffle
        Random rand = new Random();
        for (int i = 0; i < kens.length; i++) {
            int randomIndexToSwap = rand.nextInt(kens.length);
            int temp = kens[randomIndexToSwap];
            kens[randomIndexToSwap] = kens[i];
            kens[i] = temp;
        }
        Arrays.sort(kens);
        int smallCursor = (n-1)/2;
        int bigCursor = n-1;
        int passengers = 0;
        while (smallCursor >= 0 && bigCursor >= 0) {
            if (kens[smallCursor] * 2 <= kens[bigCursor]) {
                kens[smallCursor] = -1;
                passengers++;
                smallCursor--;
                bigCursor--;
            } else {
                smallCursor--;
            }
        }
        System.out.println(n-passengers);
    }
}
