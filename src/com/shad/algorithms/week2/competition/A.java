package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {
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
        int k = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        int currentVal;
        for (int i = 0; i < k; i++) {
            currentVal = scanner.nextInt();
            if (currentVal < array[0] || currentVal > array[n - 1]) {
                System.out.println("NO");
            } else {
                if (binSearch(array, 0, n-1, currentVal)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    static boolean binSearch(int[] array, int l, int r, int searchVal) {
        if (l == r) {
            return array[l] == searchVal;
        } else {
            int mid = l + (r-l)/2;
            if (array[mid] > searchVal) {
                if (mid <= l) {
                    return false;
                } else {
                    return binSearch(array, l, mid - 1, searchVal);
                }
            } else if(array[mid] < searchVal) {
                if (mid >= r) {
                    return false;
                } else {
                    return binSearch(array, mid + 1, r, searchVal);
                }
            } else {
                return true;
            }
        }
    }
}
