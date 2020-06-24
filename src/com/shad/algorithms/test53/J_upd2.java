package com.shad.algorithms.test53;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.StringTokenizer;

public class J_upd2 {
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
//        DecimalFormat df = new DecimalFormat("#.000000");
//        df.setRoundingMode(RoundingMode.HALF_UP);
        BigDecimal[] a = new BigDecimal[n];
        a[0] = BigDecimal.valueOf(scanner.nextDouble());
        for (int i = 1; i < n; i++) {
            a[i] = BigDecimal.valueOf(scanner.nextDouble());
            a[i] = a[i].multiply(a[i-1], MathContext.DECIMAL128);
        }
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            if (l == 0) {
                double result = Math.pow(a[r].doubleValue(), 1.0/(r-l+1.0));
                System.out.println(String.format("%.6f",result));
            } else {
                double result = Math.pow(a[r].divide(a[l-1], 20, BigDecimal.ROUND_HALF_UP).doubleValue(), 1.0/(r-l+1.0));
                System.out.println(String.format("%.6f",result));
            }
        }
//        System.out.println(String.format("%.6f",72.09));
    }
}
