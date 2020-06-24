package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class G2 {
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

    static class Jewel implements Comparable<Jewel>{
        int i;
        int v,w;
        double c = 1;

        public Jewel(int i, int v, int w) {
            this.i = i;
            this.v = v;
            this.w = w;
        }

        public void setC(double c) {
            this.c = c;
        }

        @Override
        public int compareTo(Jewel other) {
            double otherVal = other.v - other.c * other.w;
            double currentVal = this.v - this.c * this.w;
            if (currentVal > otherVal) {
                return 1;
            } else if (currentVal == otherVal) {
                return 0;
            } else {
                return -1;
            }
        }

        public double getValue() {
            return (double)this.v - this.c * this.w;
        }
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        Jewel[] jewels = new Jewel[n];
        int maxV = 0, minW = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            if (maxV < v) {
                maxV = v;
            }
            if (minW > w) {
                minW = w;
            }
            jewels[i] = new Jewel(i+1, v, w);
        }
        double l = 0;
        double r = (double)maxV*k/minW*k;
        double mid;
        for (int i = 0; i < 70; i++) {
            mid = l + (r - l)/2.0;
            if (check(jewels, mid, k)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        StringBuilder builder = new StringBuilder();
        int[] numbers = new int[k];
        for (int i = jewels.length - k, j = 0; i < jewels.length; i++, j++) {
            numbers[j] = jewels[i].i;
        }
        Arrays.sort(numbers);
        for (int i = 0; i < k; i++) {
            builder.append(numbers[i]).append("\n");
        }
        System.out.println(builder.toString());
    }

    public static boolean check(Jewel[] jewels, double C, int K) {
        for (int i = 0; i < jewels.length; i++) {
            jewels[i].setC(C);
        }
        //shuffling
//        Random rand = new Random();
//        for (int i = 0; i < jewels.length; i++) {
//            int randomIndexToSwap = rand.nextInt(jewels.length);
//            Jewel temp = jewels[randomIndexToSwap];
//            jewels[randomIndexToSwap] = jewels[i];
//            jewels[i] = temp;
//        }
        Arrays.sort(jewels);
        double temp = 0;
        for (int i = jewels.length - K; i < jewels.length; i++) {
            temp += jewels[i].getValue();
        }
        return temp >= 0;
    }
}
