package com.shad.algorithms.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
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

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        HashMap<Long, Integer> array = new HashMap<>();
        int max = 0;
        long maxVal = -1;
        for (int i = 0; i < n; i++) {
            long val = scanner.nextLong();
            int count;
            if (array.containsKey(val)) {
                count= array.get(val);
                count += 1;
                array.put(val, count);
            } else {
                count = 1;
                array.put(val, count);
            }
            if (count > max) {
                maxVal = val;
                max = count;
            } else if (count == max && val > maxVal) {
                maxVal = val;
                max = count;
            }
        }
        System.out.println(maxVal);
    }
}
