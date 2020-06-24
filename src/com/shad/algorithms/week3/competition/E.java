package com.shad.algorithms.week3.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E {
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
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println(sort(array, 0, n-1));
//        testMerge();
//        testSort();
    }

    public static long sort(int[] array, int l, int r) {
        if (l >= r) {
            return 0;
        } else {
            int mid = l + (r-l)/2;
            long inversionsCount = 0;
            inversionsCount = sort(array, l, mid);
            inversionsCount += sort(array, mid + 1, r);
            inversionsCount += merge(array, l, mid, mid + 1, r);
            return inversionsCount;
        }
    }

    public static long merge(int[] array, int l1, int r1, int l2, int r2) {
        int size = r2 - l1 + 1;
        int[] temp = new int[size];
        int leftCursor = l1;
        int righCursor = l2;
        int leftSize = r1 - l1 + 1;
        int rightSize = r2 - l2 + 1;
        long inversionsCount = 0;
        for (int i = 0; i < size; i++) {
            if (leftCursor <= r1 && righCursor <= r2) {
                if (array[leftCursor] <= array[righCursor]) {
                    temp[i] = array[leftCursor];
                    leftCursor++;
                } else {
                    temp[i] = array[righCursor];
                    righCursor++;
                    inversionsCount += leftSize - (leftCursor-l1);
                }
            } else if (leftCursor > r1) {
                temp[i] = array[righCursor];
                righCursor++;
            } else {
                temp[i] = array[leftCursor];
                leftCursor++;
            }
        }
        for (int i = l1, j = 0; i <= r2; i++, j++) {
            array[i] = temp[j];
        }
        return inversionsCount;
    }

    public static void testMerge() {
        int[] array = new int[]{3,4,1,2};
        merge(array, 0,1,2,3);
        if (!Arrays.equals(new int[]{1,2,3,4}, array)) {
            System.out.println("Error!");
        }

        array = new int[]{3,4,5,1,2};
        merge(array, 0,2,3,4);
        if (!Arrays.equals(new int[]{1,2,3,4,5}, array)) {
            System.out.println("Error!");
        }

        array = new int[]{4,5,1,2};
        long inversionCount = merge(array, 0,1,2,3);
        if (!Arrays.equals(new int[]{1,2,4,5}, array) && inversionCount == 4) {
            System.out.println("Error!");
        }

        array = new int[]{5,4};
        inversionCount = merge(array, 0,0,1,1);
        if (!Arrays.equals(new int[]{4,5}, array) && inversionCount == 1) {
            System.out.println("Error!");
        }
    }

    public static void testSort() {
        int[] array = new int[]{3,4,1,2};
        sort(array, 0, 3);
        if (!Arrays.equals(new int[]{1,2,3,4}, array)) {
            System.out.println("Error!");
        }
        array = new int[]{3,1,2};
        sort(array, 0, 2);
        if (!Arrays.equals(new int[]{1,2,3}, array)) {
            System.out.println("Error!");
        }
        array = new int[]{5,4,2,1};
        long inversionCount = sort(array, 0, 3);
        if (!Arrays.equals(new int[]{1,2,4,5}, array) && inversionCount == 6) {
            System.out.println("Error!");
        }
        for (int n = 1; n < 100 ; n++) {
            for (int j = 0; j < 1000; j++) {
                int[] first = new int[n];
                int[] second = new int[n];
                array = new int[n];
                for (int i = 0; i < n; i++) {
                    int randomNum = 0 + (int)(Math.random() * ((100 - 0) + 1));
                    first[i] = randomNum;
                    second[i] = randomNum;
                    array[i] = randomNum;
                }
                sort(first, 0, n-1);
                Arrays.sort(second);
                for (int i = 0; i < n; i++) {
                    if (first[i] != second[i]) {
                        System.out.println("Error!");
                        System.out.println(array);
                    }
                }
            }
        }
    }

}
