package com.shad.algorithms.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D_upd {

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

    public D_upd(long n) {
        this.n = n;
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        for (long i = 1 ; i < 10000000; i++){
            D d = new D(i);
            D_upd d_upd = new D_upd(i);
            if (d.getPairs() != d_upd.getPairs()) {
                System.out.println(i);
                System.out.println(d_upd.getPairs());
                System.out.println("right answer " + d.getPairs());
                scanner.nextLine();
            }
            if (i % 999999 == 0) {
                System.out.println(i);
            }
        }
//        FastScanner scanner = new FastScanner(System.in);
//        long n = scanner.nextLong();
//        D_upd d_upd = new D_upd(n);
//        System.out.println(d_upd.getPairs());
    }

    public int getPairs() {
        int yLimit = (int)Math.pow(n, 1.0/3.0) + 1;
        int counter = 0;
        double testVal;
        for (int y = 0; y <= yLimit; y++) {
            testVal = Math.pow(n - Math.pow(y, 3), 0.5);
            if ((int)testVal - testVal == 0) {
                if (y != testVal) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
