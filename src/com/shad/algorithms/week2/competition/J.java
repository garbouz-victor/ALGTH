package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class J {
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

    private static class Solver {
        int[] a;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            int n = scanner.nextInt();
            a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
        }

        private double getVal(int l, int r, double x) {
            return Math.abs(a[r] - a[l] - x*(1-l+1));
        }

        private double getMaxSum(double x) {
            double ans = a[0], sum = 0.0, min_sum = 0;
            for (int r = 0; r < a.length ; r++) {
                sum += a[r] - x;
                ans = Math.max(ans, sum - min_sum);
                min_sum = Math.min(min_sum, sum);
            }
            return ans;
        }

        private double getMinSum(double x) {
            double ans = a[0], sum = 0.0, max_sum = 0;
            for (int r = 0; r < a.length ; r++) {
                sum += a[r] - x;
                ans = Math.min(ans, sum - max_sum);
                max_sum = Math.max(max_sum, sum);
            }
            return ans;
        }

        public double getAnswer() {
            double l = Integer.MIN_VALUE;
            double r = Integer.MAX_VALUE;
            double answer = r;
            for (int i = 0; i < 200; i++) {
                double part = (r-l)/3.0;
                double answerLeft = Math.max(Math.abs(getMaxSum(l+part)), Math.abs(getMinSum(l+part)));
                double answerRight = Math.max(Math.abs(getMaxSum(r-part)), Math.abs(getMinSum(r-part)));
                if (answerLeft > answerRight) {
                    l = l+part;
                    if (answer > answerRight) {
                        answer = answerRight;
                    }
                } else {
                    r = r -part;
                    if (answer > answerLeft) {
                        answer = answerLeft;
                    }
                }
            }
            return answer;
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.getAnswer());
    }
}
