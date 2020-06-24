package com.shad.algorithms.week1.competition;

import java.io.*;
import java.util.Arrays;
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
    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
//        int n = scanner.nextInt();
//        int[] a = new int[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = scanner.nextInt();
//        }
//        System.out.println(getAnswerSlow(n, a));
        for (int n = 100000; n < 1000000; n++) {
            for (int j = 0; j < 10 ; j++) {
                int[] a = new int [n];
                StringBuilder builder = new StringBuilder();
                builder.append(n).append("\n");
                for (int i = 0; i < n; i++) {
                    a[i] = (int)(Math.random() * 1);
                    if (i != n-1) {
                        builder.append(a[i]).append(" ");
                    } else {
                        builder.append(a[i]);
                    }
                }
                builder.append("\n");
                System.out.println("generated!");
//                Answer aRight = getAnswerSlow(n, a);
//                System.out.println("calculated!");
                MaxOnSection_v2 max = new MaxOnSection_v2(new ByteArrayInputStream(builder.toString().getBytes()));

                MaxOnSection max1 = new MaxOnSection(a, n);
                MaxOnSection.Answer a1 = max1.getMaxSectionMultMin(0, n-1);

//                MaxOnSection.Answer a2 = max.getMaxSectionMultMin(0, n-1);
//                MaxOnSection_v2.Answer a2 = max.getAnswer();
                System.out.println("calculated!");

//                if (a1.answer != a2.answer || a1.l != a2.l || a1.r != a2.r) {
//                    long sum = 0;
//                    int min = a[a2.l];
//                    for (int l = a2.l; l <= a2.r ; l++) {
//                        if (min  > a[l]) {
//                            min = a[l];
//                        }
//                        sum += a[l];
//                    } if (sum * min != a2.answer) {
//                        System.out.println(Arrays.toString(a));
//                        System.out.println(a1);
//                        System.out.println(a2);
//                        scanner.nextChar();
//                    }
//                }
            }
            if (n%999 == 0) {
                System.out.println(n);
            }
//            System.out.println(Arrays.toString(a));
//            System.out.println(a1);
//            System.out.println(a2);
//            scanner.nextInt();
        }

    }

    static class Answer {
        long max;
        int l,r;

        public Answer(long max,int l,int r) {
            this.max = max;
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(max).append("\n").append(l).append(" ").append(r);
            return builder.toString();
        }
    }

    public static Answer getAnswerSlow(int n, int[] a) {
        long max = a[0];
        int lmax = 0, rmax = 0;
        int currentMin;
        int currentSum;
        long currentVal;
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                currentMin = a[l];
                currentSum = 0;
                for (int i = l; i <= r; i++) {
                    if (currentMin > a[i]) {
                        currentMin = a[i];
                    }
                    currentSum += a[i];
                }
                currentVal = currentSum * currentMin;
                if (max < currentVal) {
                    max = currentVal;
                    lmax = l;
                    rmax = r;
                }
            }
        }
        return new Answer(max, lmax, rmax);
    }
}
