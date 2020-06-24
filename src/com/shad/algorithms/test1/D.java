package com.shad.algorithms.test1;

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
    long n;
    public D(long n) {
        this.n = n;
    }

    public int getPairs() {
        int counter = 0;
        long xLimit = (long)Math.pow(n, 0.5) + 1;
        long yLimit = (long)Math.pow(n, 1.0/3.0) + 1;
        long valX;
        long valY;
        for (long x = 0; x <= xLimit; x++) {
            valX = x*x;
            for (long y = 0; y <= yLimit; y++) {
                valY = y*y*y;
                if (valX + valY == n) {
                    if (y != x) {
                        counter++;
                    }
                } else if (valX > n) {
                    break;
                }
            }
        }
        return counter;
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        long n = scanner.nextLong();
        long counter = 0;
        int xLimit = (int)Math.sqrt(n) + 1;
        int yLimit = (int)Math.pow(n, 1.0/3) + 1;
        int valX;
        int valY;
        for (int x = 0; x <= xLimit; x++) {
            valX = x*x;
            if (valX > n) {
                break;
            }
            for (int y = 0; y <= yLimit; y++) {
                valY = y*y*y;
                if (valX + valY == n) {
                    counter++;
                } else if (valX > n) {
                    break;
                }
            }
        }
        System.out.println(counter);
    }
}
