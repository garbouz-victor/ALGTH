package com.shad.algorithms.week1.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxOnSection {
    int []array;
    int []b;
    int n;

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

    public MaxOnSection(int[] a, int n) {
        this.array = a;
        this.n = n;
        b = new int[n];
        b[0] = a[0];
        for (int i = 1; i < n; i++) {
            b[i] = b[i-1] + a[i];
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

    public Answer getMaxSection_v2() {
        long ans = array[0], sum = 0, min_sum = 0;
        int ans_l = 0,
                ans_r = 0,
                min_pos = -1;
        int currentMin = 0;
        for (int r = 0; r < n; ++r) {
            sum = getSumOnSection(ans_l, r);

            int minPosition = getMinPosition(ans_l, r);

            long cur = (sum - min_sum) * array[minPosition];
            if (cur > ans) {
                ans = cur;
                ans_l = min_pos + 1;
                ans_r = r;
            }

            if (sum < min_sum) {
                min_sum = sum * array[minPosition];
                min_pos = r;
            }
        }
        return new Answer(ans, ans_l, ans_r);
    }

    public Answer getMaxWithMinSection() {
        int []a = new int[array.length];
        for (int i = 1; i < array.length; i++) {
            a[i] = array[i] - array[i-1];
        }
        MaxOnSection newMax = new MaxOnSection(a, n);
        return newMax.getMaxSection();
    }

    public Answer getMaxSectionMultMin(int l, int r) {
        if (l > r) {
            return new Answer(0, r, r);
        }
        if (l == r) {
            return new Answer(array[l] * array[l], l, r);
        }
        int minPosition = getMinPosition(l, r);
        long lrSum = getSumOnSection(l,r);
        Answer left = getMaxSectionMultMin(l,minPosition - 1);
        Answer right = getMaxSectionMultMin(minPosition + 1 , r);
        Answer full = new Answer(array[minPosition] * lrSum, l, r);
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

    int getMinPosition(int l, int r) {
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
        MaxOnSection section_upd = new MaxOnSection(new int[]{3, 1, 6, 4, 5, 2}, 6);
        System.out.println(section_upd.getMaxSection_v2());
//
//        MaxOnSection section2 = new MaxOnSection(new int[]{3, 1, 6, 4, 5, 2}, 6);
//        System.out.println(section2.getMaxSectionMultMin(0, 5));

//        System.out.println(section2.getMinPosition(0, 5));
//        System.out.println(section2.getSumOnSection(2,4));

//        FastScanner scanner = new FastScanner(System.in);
//        int n = scanner.nextInt();
//        int[] a = new int[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = scanner.nextInt();
//        }
//        MaxOnSection section = new MaxOnSection(a, n);
//        System.out.println(section.getMaxSectionMultMin(0, n-1));
    }
}
