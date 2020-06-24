package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class H {
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
        int xMin = Integer.MAX_VALUE;
        int xMax = 0;
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            if (x[i] < xMin) {
                xMin = x[i];
            }
            if (x[i] > xMax) {
                xMax = x[i];
            }
        }
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = scanner.nextInt();
        }
        double l = xMin;
        double r = xMax;
        double l1, r1, portion;
        double lTime = calcTime(x, v, l, n);
        double rTime = calcTime(x, v, r, n);
        double minTime = Math.min(lTime, rTime), currentMin;
        for (int i = 0; i < 200; i++) {
            portion = (r - l)/3.0;
            l1 = l + portion;
            r1 = r - portion;
            lTime = calcTime(x, v, l1, n);
            rTime = calcTime(x, v, r1, n);
            if (lTime > rTime) {
                l = l1;
            } else {
                r = r1;
            }
            currentMin = Math.min(lTime, rTime);
            if (minTime > currentMin) {
                minTime = currentMin;
            }
        }
        System.out.println(minTime);
    }

    public static double calcTime(int[] x, int[] v, double point, int n) {
        double result = 0, currentResult;
        for (int i = 0; i < n; i++) {
            currentResult = Math.abs(point - x[i])/v[i];
            if (currentResult > result) {
                result = currentResult;
            }
        }
        return result;
    }
}
