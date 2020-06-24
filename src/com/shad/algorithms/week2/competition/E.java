package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E {
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
        double C = scanner.nextDouble();
        double l = 0.0;
        double r = C*C + Math.sqrt(C) + 10;
        double m = l, result;
        for (int i = 0; i < 200; i++) {
            m = l+(r-l)/2.0;
            result = function(m);
            if (result > C) {
                r = m;
            } else if (result == C) {
                break;
            } else {
                l = m;
            }
        }
        System.out.println(m);
    }

    public static double function(double x) {
        return Math.pow(x, 2) + Math.sqrt(x);
    }
}
