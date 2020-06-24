package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class L_upd {
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
        int m = scanner.nextInt();
        if (n >= m) {
            TreeSet<Integer> drills = new TreeSet<>();
            for (int i = 0; i < n ; i++) {
                drills.add(scanner.nextInt());
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                int dub = scanner.nextInt();
                if (drills.contains(dub)){
                    System.out.println(0);
                    return;
                } else {
                    Integer lower = drills.lower(dub);
                    Integer higher = drills.higher(dub);
                    int currentMin;
                    if (lower != null && higher != null) {
                       currentMin = Math.min(Math.abs(dub - lower), Math.abs(dub - higher));
                    } else if (lower != null) {
                        currentMin = Math.abs(dub - lower);
                    } else {
                        currentMin = Math.abs(dub - higher);
                    }

                    if (min > currentMin) {
                        min = currentMin;
                    }
                }
            }
            System.out.println(min);
        } else {
            int[] drills = new int[n];
            for (int i = 0; i < n; i++) {
                drills[i] = scanner.nextInt();
            }
            TreeSet<Integer> dub = new TreeSet<>();
            for (int i = 0; i < m; i++) {
                dub.add(scanner.nextInt());
            }
            int min = Integer.MAX_VALUE;
            for (int drill : drills) {
                if (dub.contains(drill)) {
                    System.out.println(0);
                    return;
                } else {
                    Integer lower = dub.lower(drill);
                    Integer higher = dub.higher(drill);
                    int currentMin;
                    if (lower != null && higher != null) {
                        currentMin = Math.min(Math.abs(drill - lower), Math.abs(drill - higher));
                    } else if (lower != null) {
                        currentMin = Math.abs(drill - lower);
                    } else {
                        currentMin = Math.abs(drill - higher);
                    }
                    if (min > currentMin) {
                        min = currentMin;
                    }
                }
            }
            System.out.println(min);
        }
    }
}
