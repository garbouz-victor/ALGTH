package com.shad.algorithms.week8.competition;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class B {
    private static class RMQ {
        int []a, segments;
        int n;
        public RMQ(InputStream is) {
            Scanner scanner = new Scanner(is);
            n = scanner.nextInt();
            a = new int[n+1];
            segments = new int[4*n+1];
            PrintWriter writer = new PrintWriter(System.out);
            for (int i = 1; i <= n; i++) {
                a[i] = scanner.nextInt();
            }
            build(1, 1, n);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] commands = scanner.nextLine().split(" ");
                int val1 = Integer.valueOf(commands[1]);
                int val2 = Integer.valueOf(commands[2]);
                if ("min".equals(commands[0])) {
                    writer.println(min(val1, val2, 1, 1, n));
                    writer.flush();
                } else {
                    setPosition(val1, val2, 1, 1, n);
                }
            }
        }

        private void build(int v, int sl, int sr) {
            if (sl == sr) {
                segments[v] = a[sl];
            } else {
                int m = (sl + sr)/2;
                build(v*2, sl, m);
                build(v*2+1, m+1, sr);
                segments[v] = Math.min(segments[v*2], segments[v*2+1]);
            }
        }

        private int min(int ql, int qr, int v, int sl, int sr) {
            if (ql <= sl && sr <= qr) {
                return segments[v];
            }
            if (qr < sl || sr < ql) {
                return Integer.MAX_VALUE;
            }
            int m = (sl + sr)/2;
            int ans1 = min(ql, qr, v*2, sl, m);
            int ans2 = min(ql, qr, v*2+1, m+1, sr);
            return Math.min(ans1, ans2);
        }

        private void setPosition(int i, int x, int v, int sl, int sr) {
            if (sl == sr) {
                segments[v] = x;
                return;
            } else {
                int m = (sl + sr)/2;
                if (i <= m) {
                    setPosition(i, x, v*2, sl, m);
                } else {
                    setPosition(i, x, v*2+1, m+1, sr);
                }
                segments[v] = Math.min(segments[v*2], segments[v*2+1]);
            }
        }
    }

    public static void main(String... args) {
        RMQ s = new RMQ(System.in);
    }
}
