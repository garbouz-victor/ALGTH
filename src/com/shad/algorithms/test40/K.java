package com.shad.algorithms.test40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class K {
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
        int[] array = new int[n];
        HashMap<Integer, Integer> arrayPositions = new HashMap<>();
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
            arrayPositions.put(array[i], i);
        }
        StringBuilder builder = new StringBuilder();
        int m = arrayPositions.size();
        for (int i = 0; i < n ; i++) {
            if (arrayPositions.get(array[i]) == i) {
                builder.append(array[i]).append(" ");
            }
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(m);
        System.out.println(builder.toString());
    }
}
