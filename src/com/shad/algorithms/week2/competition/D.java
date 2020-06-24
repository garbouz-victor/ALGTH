package com.shad.algorithms.week2.competition;

import java.io.*;
import java.util.StringTokenizer;

public class D {
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
//        FastScanner scanner = new FastScanner(System.in);
//        int w = scanner.nextInt();
//        int h = scanner.nextInt();
////        if (w > h) {
////            int temp = h;
////            w = h;
////            h = temp;
////        }
//        int n = scanner.nextInt();
//        int l = 1, r = n;
//        int mid;
//        int min = getLength(w, h, n, n);
//        while (r >= l) {
//            mid = l + (r-l)/2;
//            int l1 = getLength(w, h, mid, n);
//            int l2 = getLength(w, h, mid + 1, n);
//            if (l1 < l2) {
//                r = mid - 1;
//                min = l1;
//            } else {
//                l = mid + 1;
//                min = l2;
//            }
//        }
//        System.out.println(min);

//        for (int n = 1000; n < 100000 ; n++) {
//            for (int i = 0; i < 1000000; i++) {
//                String test = getTestStr(10, 1000, n);
//                long res1 = getTestAnswer(new ByteArrayInputStream(test.getBytes()));
//                long res2 = getAnswer(new ByteArrayInputStream(test.getBytes()));
//                if (res2 != res1) {
//                    System.out.println(test);
//                    System.out.println("res1 = " + res1);
//                    System.out.println("res2 = " + res2);
//                    return;
//                }
//            }
//        }


//        testGetLength();
        System.out.println(getTestAnswer(System.in));
    }

    public static long getLength(int w, int h, int k, int n) {
//        int sqrk = k*k;
//        if (n > sqrk) {
//            int tail = n - sqrk;
//            tail = (int)Math.ceil((double)tail / k);
//            return Math.max(k * h, (k + tail) * w);
//        } else {
            int columns = (int)Math.ceil((double)n / k);
            return Math.max((long)k * h, (long)columns * w);
//        }
    }

    public static void testGetLength() {
        long res = getLength(2, 3, 4, 10);
        if (res != 12) {
            System.out.println("Error!");
        }
        res = getLength(3, 2, 4, 10);
        if (res != 9) {
            System.out.println("Error!");
        }
        res = getLength(3, 1, 4, 10);
        if (res != 9) {
            System.out.println("Error!");
        }
    }

    public static long getTestAnswer(InputStream is) throws IOException {
        FastScanner scanner = new FastScanner(is);
        int w = scanner.nextInt();
        int h = scanner.nextInt();
        int n = scanner.nextInt();
        int l = 1, r = n;
        int mid;
        long min = getLength(w, h, l, n);
        while (r >= l) {
            mid = l + (r-l)/2;
            long l1 = getLength(w, h, mid, n);
            long l2 = getLength(w, h, mid + 1, n);
            if (l1 < l2) {
                r = mid - 1;
                min = l1;
            } else {
                l = mid + 1;
                min = l2;
            }
        }
        return min;
    }

    public static long getAnswer(InputStream is) throws IOException {
        FastScanner scanner = new FastScanner(is);
        int w = scanner.nextInt();
        int h = scanner.nextInt();
        int n = scanner.nextInt();
        int l = 1, r = n;
        long min = getLength(w, h, l, n);
        for (int i = l; i <= r; i++) {
            int columns = (int)Math.ceil((double)n / i);
            long current = Math.max((long)i * h, (long)columns * w);
            if (current < min) {
                min =  current;
            }
        }
        return min;
    }

    public static String getTestStr(int maxW, int maxH, int maxN) {
        int w = 1 + (int)(Math.random() * ((maxW - 1) + 1));
        int h = 1 + (int)(Math.random() * ((maxH - 1) + 1));
//        int n = 1 + (int)(Math.random() * ((maxN - 1) + 1));
        int n = maxN;
        return "" + w + " " + h + " " + n;
    }
}
