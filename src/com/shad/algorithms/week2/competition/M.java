package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class M {
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
        System.out.println(Math.min(firstStrategy(array, n), secondStrategy(array, n)));
    }

    public static int firstStrategy(int[] array, int n) {
        TreeMap<Integer, Integer> kens = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int val = array[i];
            if (kens.containsKey(val)) {
                int count = kens.get(val);
                count++;
                kens.put(val, count);
            } else {
                kens.put(val, 1);
            }
        }
        int removedBig = 0;
        while (!kens.isEmpty()) {
            Integer small = kens.firstKey();
            Integer big = small * 2;
            if (kens.containsKey(big)) {
                Integer bigCount = kens.get(big);
                Integer smallCount = kens.get(small);
                bigCount--;
                removedBig++;
                if (bigCount == 0) {
                    kens.remove(big);
                } else {
                    kens.put(big, bigCount);
                }
                smallCount--;
                if (smallCount == 0) {
                    kens.remove(small);
                } else {
                    kens.put(small, smallCount);
                }
            } else {
                big = kens.higherKey(big);
                if (big == null) {
                    break;
                } else {
                    Integer bigCount = kens.get(big);
                    Integer smallCount = kens.get(small);
                    bigCount--;
                    removedBig++;
                    if (bigCount == 0) {
                        kens.remove(big);
                    } else {
                        kens.put(big, bigCount);
                    }
                    smallCount--;
                    if (smallCount == 0) {
                        kens.remove(small);
                    } else {
                        kens.put(small, smallCount);
                    }
                }
            }
        }
        int tail = 0;
        for (Integer val : kens.values()) {
            tail += val;
        }
        return tail + removedBig;
    }

    public static int secondStrategy(int[] array, int n) {
        TreeMap<Integer, Integer> kens = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int val = array[i];
            if (kens.containsKey(val)) {
                int count = kens.get(val);
                count++;
                kens.put(val, count);
            } else {
                kens.put(val, 1);
            }
        }
        int removedBig = 0;
        while (!kens.isEmpty()) {
            Integer big = kens.lastKey();
            Integer small = big/2;
            if (kens.containsKey(small)) {
                Integer bigCount = kens.get(big);
                Integer smallCount = kens.get(small);
                bigCount--;
                removedBig++;
                if (bigCount == 0) {
                    kens.remove(big);
                } else {
                    kens.put(big, bigCount);
                }
                smallCount--;
                if (smallCount == 0) {
                    kens.remove(small);
                } else {
                    kens.put(small, smallCount);
                }
            } else {
                small = kens.lowerKey(small);
                if (small == null) {
                    break;
                } else {
                    Integer bigCount = kens.get(big);
                    Integer smallCount = kens.get(small);
                    bigCount--;
                    removedBig++;
                    if (bigCount == 0) {
                        kens.remove(big);
                    } else {
                        kens.put(big, bigCount);
                    }
                    smallCount--;
                    if (smallCount == 0) {
                        kens.remove(small);
                    } else {
                        kens.put(small, smallCount);
                    }
                }
            }
        }
        int tail = 0;
        for (Integer val : kens.values()) {
            tail += val;
        }
        return tail + removedBig;
    }
}
