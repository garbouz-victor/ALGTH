package com.shad.algorithms.test53;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
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
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextLong();
        }
        HashSet<Long> setA = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for (int i = n-1; i >= 0; i--) {
            if (setA.contains(a[i])) {
                builder.append(a[i]).append(" ");
            } else {
                setA.add(a[i]);
            }
        }
//        if (builder.length() > 0) {
//            builder.deleteCharAt(builder.length()-1);
//        }

        if (builder.length() == 0 || n == setA.size()) {
            System.out.println(0);
            System.out.println("");
        } else {
            String answer = builder.reverse().toString();
//            String[] array = answer.split(" ");
//            System.out.println(array.length);
            StringBuilder b = new StringBuilder();
            b.append(n - setA.size()).append("\n").append(answer.trim());
//            System.out.println(n - setA.size());
//            System.out.println(answer.trim());
            System.out.println(b.toString());
        }
    }
}
