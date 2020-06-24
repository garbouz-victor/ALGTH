package com.shad.algorithms.week3.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G_upd {
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

    public static class MinMaxGetter {
        int[] array;
        int N;
        int l,r;

        public MinMaxGetter(int N) {
            this.N = N;
            this.array = new int[N];
            this.l = 0;
            this.r = 0;
        }

        public void insert(int val) {
            if (l == r) {
                array[r] = val;
                r++;
            } else {
                array[r] = val;
                Arrays.sort(array, l, ++r);
            }
        }

        public int getMin() {
            return array[l++];
        }

        public int getMax() {
            return array[--r];
        }
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        StringBuilder builder = new StringBuilder();
        MinMaxGetter minMax = new MinMaxGetter(n);
        for (int i = 0; i < n; i++) {
            String operation = scanner.nextLine();
            if (operation.contains("Insert")) {
                minMax.insert(Integer.valueOf(operation.substring(7, operation.length()-1)));
            } else if (operation.equals("GetMin")) {
                builder.append(minMax.getMin()).append("\n");
            } else if (operation.equals("GetMax")) {
                builder.append(minMax.getMax()).append("\n");
            }
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }
}
