package com.shad.algorithms.week3.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

    private static int x0, x1;
    private static long a,b,c;
    private static long MOD = (long)Math.pow(2, 32);

    public static int next() {
        long t = (((x0 % MOD) * (a % MOD))%MOD + ((x1)%MOD * b%MOD)%MOD + c%MOD) % MOD;
        x0 = x1;
        x1 = (int)t;
        return x0 >>> 2;
    }

    static class Pair implements Comparable<Pair>{
        int val;
        int position;

        public Pair(int val, int position) {
            this.val = val;
            this.position = position;
        }


        @Override
        public int compareTo(Pair other) {
            if (this.val > other.val) {
                return 1;
            } else if (this.val == other.val) {
                if (this.position > other.position) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        x0 = scanner.nextInt();
        x1 = scanner.nextInt();
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        HashMap<Integer, Integer> used = new HashMap<>();
        TreeSet<Pair> mins = new TreeSet<>();
        long myconst_10099 = 10099;
        long myconst = 1;
//        long altResult = 0;
        long result = 0;
        for (int i = 1; i <= q ; i++) {
            int position = next();
            int x = next();
            myconst = (myconst * myconst_10099)%MOD;
            used.put(position % n, x);
            mins.add(new Pair(x, position % n));
            while (used.get(mins.first().position) != mins.first().val) {
                mins.pollFirst();
            }
            result = ((mins.first().val * myconst)%MOD + result)%MOD;

//            if (i == 1) {
//                altResult += 8 * 10099;
//            }
//            if (i == 2) {
//                altResult += 8 * 10099 * 10099;
//            }
//            if (i == 3) {
//                altResult += 516 * 10099 * 10099 * 10099;
//            }
//            if (i == 4) {
//                altResult += 516 * 10099 * 10099 * 10099 * 10099;
//            }
//            if (i == 5) {
//                altResult += 516 * 10099 * 10099 * 10099 * 10099 * 10099;
//            }
//            altResult = altResult%MOD;
        }
        System.out.println(result);
//        System.out.println(altResult);
    }
}
