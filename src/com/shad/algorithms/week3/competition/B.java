package com.shad.algorithms.week3.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

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

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long k = scanner.nextLong();
        TreeMap<Integer, Long> birds = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int index = scanner.nextInt();
            int count = scanner.nextInt();
            if (birds.containsKey(index)) {
                long birdsCount = birds.get(index);
                birds.put(index, birdsCount + count);
            } else {
                birds.put(index, (long)count);
            }
        }
        long temp = 0;
        long index = 0;
        for (Integer indexKey : birds.keySet()) {
            temp += birds.get(indexKey);
            if (temp >= k) {
                index = indexKey;
                break;
            }
        }
        System.out.println(index);
    }
}
