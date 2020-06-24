package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class I {
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

    private static double xHome = 0;
    private static double yHome = 1;

    private static double xTarget = 1;
    private static double yTarget = 0;

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int vp = scanner.nextInt();
        int vf = scanner.nextInt();
        double a = scanner.nextDouble();
        double l = 0.0;
        double r = 1.0;
        double timeL = getTime(0, a, vp, vf);
        double timeR = getTime(1, a, vp, vf);
        double answer, minTime;
        if (timeL > timeR) {
            answer = r;
            minTime = timeR;
        } else {
            answer = l;
            minTime = timeL;
        }
        double l1,r1,part, timeL1, timeR1;
        for (int i = 0; i < 200; i++) {
            part = (r-l)/3.0;
            l1 = l + part;
            timeL1 = getTime(l1, a, vp, vf);
            r1 = r -part;
            timeR1 = getTime(r1, a, vp, vf);
            if (timeL1 >= timeR1) {
                l = l1;
                if (timeR1 < minTime) {
                    minTime = timeR1;
                    answer = r1;
                }
            } else {
                r = r1;
                if (timeL1 < minTime) {
                    minTime = timeL1;
                    answer = l1;
                }
            }
        }
        System.out.println(answer);
    }

    public static double getTime(double x, double y, double vp, double vf) {
        double lp = getL(xHome, yHome, x, y);
        double lf = getL(x, y, xTarget, yTarget);
        return lp/vp + lf/vf;
    }

    public static double getL(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
