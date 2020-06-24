package com.shad.algorithms.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class F {
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
        int[] a,b;
        int n,m;
        HashSet<Integer> excluded;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            m = scanner.nextInt();
            a = new int[n];
            b = new int[m];
            excluded = new HashSet<>();
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            for (int i = 0; i < m; i++) {
                b[i] = scanner.nextInt();
                excluded.add(b[i]);
            }
        }
        public Solver(int n, int m, int[] a, int[] b) {
            this.n = n;
            this.m = m;
            this.a = a;
            this.b = b;
            excluded = new HashSet<>();
            for (int e : b) {
                excluded.add(e);
            }
        }
        public int[] solve() {
            sort(0,n-1);
            return a;
        }
        public void sort(int l, int r) {
            if (l >= r) {
                return;
            } else {
                int mid = l + (r-l)/2;
                sort(l, mid);
                sort(mid + 1, r);
                merge(l, mid, mid + 1, r);
            }
        }
        public void merge(int l1, int l2, int r1, int r2) {
            int leftCursor = l1;
            int rightCursor = r1;
            int size = r2 - l1 + 1;
            for (int i = 0; i < size; i++) {
                if (leftCursor > r1) {
                    break;
                } else if (rightCursor > r2) {
                    break;
                }
                if (a[leftCursor] < a[rightCursor]) {
                    leftCursor++;
                    continue;
                } else if (a[leftCursor] > a[rightCursor]) {
                    if (!excluded.contains(a[leftCursor]) && !excluded.contains(a[rightCursor])) {
                        //swap
                        int temp = a[leftCursor];
                        a[leftCursor] = a[rightCursor];
                        a[rightCursor] = temp;
                        leftCursor++;
                        if (rightCursor+1 <= r2 && a[rightCursor] > a[rightCursor + 1]) {
                            rightCursor++;
                        }
                    } else if(excluded.contains(a[leftCursor])) {
                        leftCursor++;
                    } else {
                        rightCursor++;
                    }
                } else {
                    leftCursor++;
                }
            }
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
        simpleTest();
        int n = 10;
        for (int i = 0; i < 1000; i++) {
            int[] first = genArray(n);
            int[] second = Arrays.copyOfRange(first, 0, n);
            int[] q = Arrays.copyOfRange(first, 0, n);
            int[] ans = new Solver(n, 0, second, new int[0]).solve();
            Arrays.sort(first);
            if (!Arrays.equals(first, ans)) {
                System.out.println(Arrays.toString(q));
                System.out.println(Arrays.toString(first));
                System.out.println(Arrays.toString(ans));
                return;
            }
        }
    }

    private static void simpleTest() {
        int[] ans = new Solver(10, 0, new int[] {0, 1, 4, 4, 9, 1, 9, 9, 1, 2}, new int[0]).solve();
        if (!Arrays.equals(new int[]{0, 1, 1, 1, 2, 4, 4, 9, 9, 9}, ans)) {
            throw new RuntimeException(Arrays.toString(ans));
        }
        ans = new Solver(10, 0, new int[] {0, 1, 1, 2, 4, 4, 9, 4, 5, 7}, new int[0]).solve();
        if (!Arrays.equals(new int[]{0, 1, 1, 2, 4, 4, 4, 5, 7, 9}, ans)) {
            throw new RuntimeException(Arrays.toString(ans));
        }
    }

    public static void main(String... args) throws IOException {
//        Solver s = new Solver(System.in);
//        int[] solved = s.solve();
//        for (int i = 0; i < solved.length; i++) {
//            System.out.print(solved[i] + " ");
//        }
//        test();
        int[] ans = new Solver(10, 0, new int[] {6, 7, 1, 3, 4, 1, 6, 5, 0, 7}, new int[0]).solve();
        System.out.println(Arrays.toString(ans));
    }
}
