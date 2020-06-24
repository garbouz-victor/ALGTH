package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxOnSection_v2 {
    int []array;
    long []b;
    int n;
    int ST[][];
    int K;

    class Answer {
        long answer;
        int l,r;
        public Answer(long answer, int l, int r) {
            this.l = l;
            this.r = r;
            this.answer = answer;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(answer).append("\n").append(l+1).append(" ").append(r+1);
            return builder.toString();
        }
    }

    public MaxOnSection_v2(InputStream stream) throws IOException {
        FastScanner scanner = new FastScanner(stream);
        this.n = scanner.nextInt();
        this.array = new int[n];
        this.b = new long[n+1];
        array[0] = scanner.nextInt();
        this.b[0] = this.array[0];
        K = (int)(Math.log(n)/Math.log(2)) + 1;
        ST = new int[K][];
        ST[0] = new int[n];
        ST[0][0] = 0;
        for (int i = 1; i < n; i++) {
            this.array[i] = scanner.nextInt();
            this.b[i] = this.b[i-1] + this.array[i];
            ST[0][i] = i;
        }
        for (int k = 1; k < K; k++) {
            ST[k] = new int[n];
            int j = 0;
            while (j + Math.pow(2, k-1) < n) {
                if (array[ST[k-1][j]] > array[ST[k-1][j + (int)Math.pow(2, k-1)]]) {
                    ST[k][j] = ST[k-1][j + (int)Math.pow(2, k-1)];
                } else {
                    ST[k][j] = ST[k-1][j];
                }
                j+=1;
            }
        }
    }

    public Answer getMaxSection() {
        long s = 0;
        long smin = array[0];
        long currAnswer = s;
        int curr = 0, curl = 0, cur = 0;
        for (int i = 0; i < n; i++) {
            s += array[i];
            if (s - smin > currAnswer) {
                currAnswer = s - smin;
                curr = i;
                curl = cur + 1;
            }
            if (s < smin) {
                cur = i;
                smin = s;
            }
        }
        return new Answer(currAnswer, curl, curr);
    }

    public Answer getAnswer() {
        return getMaxSectionMultMin(0, this.n-1);
    }

    public Answer getMaxSectionMultMin(int l, int r) {
        if (l > r) {
            return new Answer(0, r, r);
        }
        if (l == r) {
            return new Answer((long)array[l] * array[l], l, r);
        }
        int minPosition = getMinPosition(l, r);
        long lrSum = getSumOnSection(l,r);
        Answer left = getMaxSectionMultMin(l,minPosition - 1);
        Answer right = getMaxSectionMultMin(minPosition + 1 , r);
        Answer full = new Answer(lrSum * array[minPosition] , l, r);
        if (full.answer > left.answer) {
            if (full.answer > right.answer) {
                return full;
            } else {
                return right;
            }
        } else {
            if (left.answer > right.answer) {
                return left;
            } else {
                return right;
            }
        }
    }

//    int getMinPosition(int l, int r) {
//        int minPosition = l;
//        for (int i = l+1; i <= r; i++) {
//            if (array[i] < array[minPosition]) {
//                minPosition = i;
//            }
//        }
//        return minPosition;
//    }

    int getMinPositionSlow(int l, int r) {
        if (l == r) {
            return l;
        } else {
            int mid = (r - l)/2;
            int left = getMinPosition(l, l + mid);
            int right = getMinPosition(l + mid + 1, r);
            if (array[left] < array[right]) {
                return left;
            } else {
                return right;
            }
        }
    }

    int getMinPosition(int l, int r) {
        int k = (int)(Math.log(r - l + 1)/Math.log(2));
        int a = array[ST[k][l]];
        int b = array[ST[k][r - (int)Math.pow(2, k) + 1]];
        if (a > b) {
            return ST[k][r - (int)Math.pow(2, k) + 1];
        } else {
            return ST[k][l];
        }
    }

//    long getSumOnSection(int l, int r) {
//        long sum = 0;
//        for (int i = l; i <=r ; i++) {
//            sum += array[i];
//        }
//        return sum;
//    }

    long getSumOnSection(int l, int r) {
        if (l-1 < 0) {
            return b[r];
        }
        return b[r] - b[l - 1];
    }

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
//        MaxOnSection section = new MaxOnSection(new int[]{13, -3, -25, 20,
//                -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 1}, 16);
//        System.out.println(section.getMaxSection());
//
//        MaxOnSection section2 = new MaxOnSection(new int[]{3, 1, 6, 4, 5, 2}, 6);
//        System.out.println(section2.getMaxSectionMultMin(0, 5));

//        System.out.println(section2.getMinPosition(0, 5));
//        System.out.println(section2.getSumOnSection(2,4));
//        FastScanner scanner = new FastScanner(System.in);
        MaxOnSection_v2 section = new MaxOnSection_v2(System.in);
        System.out.println(section.getAnswer());
//        System.out.println(section.getMinPosition(0,section.n-1));
    }
}
