package com.shad.algorithms.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class F_upd {
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
        private int[] a;
        private HashSet<Integer> b;
        int n,m;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            a = new int[n];
            m = scanner.nextInt();
            b = new HashSet<>();
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            for (int i = 0; i < m; i++) {
                b.add(scanner.nextInt());
            }
        }

        public Solver(int[] a, int[] b) {
            this.a = a;
            this.b = new HashSet<>();
            for (int bi:b) {
                this.b.add(bi);
            }
            this.n = a.length;
            this.m = b.length;
        }

        public int[] solve(){
            for (int i = 0; i < n; i++) {
                if (!b.contains(a[i])) {
                    int minIndex = getMinIndex(i, n);
                    swap(i, minIndex);
                }
            }
            return a;
        }

        private int getMinIndex(int l, int r) {
            int minIndex = l;
            for (int i = l+1; i < r; i++) {
                if (a[minIndex] > a[i] && !b.contains(a[i])) {
                    minIndex = i;
                }
            }
            return minIndex;
        }

        private void swap(int i1, int i2) {
            if (i1 == i2) {
                return;
            }
            int temp = a[i1];
            a[i1] = a[i2];
            a[i2] = temp;
        }
    }

    private static int[] genArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random()*n);
        }
        return array;
    }

    private static void test() {
        int n = 100;
        for (int i = 0; i < 10000; i++) {
            int[] first = genArray(n);
            int[] second = Arrays.copyOfRange(first, 0, n);
            int[] q = Arrays.copyOfRange(first, 0, n);
            int[] ans = new Solver(second, new int[0]).solve();
            Arrays.sort(first);
            if (!Arrays.equals(first, ans)) {
                System.out.println(Arrays.toString(q));
                System.out.println(Arrays.toString(first));
                System.out.println(Arrays.toString(ans));
                return;
            }
        }
    }

    public static void main(String... args) throws IOException {
//        test();
        Solver s = new Solver(System.in);
        int[] solved = s.solve();
        for (int i = 0; i < solved.length; i++) {
            System.out.print(solved[i] + " ");
        }
    }
}
