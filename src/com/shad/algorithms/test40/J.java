package com.shad.algorithms.test40;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private static DecimalFormat df = new DecimalFormat("0.000000");

    public static void main(String...args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        double[] array = new double[n+1];
        double sum = 0;
        array[0] = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += scanner.nextDouble();
            array[i] = sum;
        }
        StringBuilder builder = new StringBuilder();
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            sum = array[r+1] - array[l];
            builder.append(df.format(sum/(r-l+1))).append("\n");
        }
        System.out.println(builder.toString());
    }
}
