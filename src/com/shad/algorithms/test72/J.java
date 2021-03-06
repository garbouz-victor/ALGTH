package com.shad.algorithms.test72;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        DecimalFormat df = new DecimalFormat("#.000000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        int n = scanner.nextInt();
        double[] a = new double[n];
        a[0] = scanner.nextDouble();
        a[0] *= a[0];
        for (int i = 1; i < n; i++) {
            a[i] = scanner.nextDouble();
            a[i] *= a[i];
            a[i] = a[i-1] + a[i];
        }
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            if (l == 0) {
                double result = Math.sqrt(a[r]/(r-l+1));
                System.out.println(df.format(result));
            } else {
                double result = Math.sqrt((a[r] - a[l-1])/(r-l+1));
                System.out.println(df.format(result));
            }
        }
    }
}
