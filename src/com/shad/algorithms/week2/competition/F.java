package com.shad.algorithms.week2.competition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer, TreeSet<Integer>> passengers = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int val = scanner.nextInt();
            if (passengers.containsKey(val)) {
                TreeSet<Integer> cities = passengers.get(val);
                cities.add(i);
            } else {
                TreeSet<Integer> cities = new TreeSet<>();
                cities.add(i);
                passengers.put(val, cities);
            }
        }
        int q = scanner.nextInt();
        StringBuilder builder = new StringBuilder();
        int l, r, x;
        for (int i = 0; i < q; i++) {
            l = scanner.nextInt();
            r = scanner.nextInt();
            x = scanner.nextInt();
            if (passengers.containsKey(x)) {
                TreeSet<Integer> cities = passengers.get(x);
                if (cities.contains(l)) {
                    builder.append(1);
                } else if(cities.contains(r)) {
                    builder.append(1);
                } else {
                    Integer graterThanL = cities.higher(l);
                    if (graterThanL == null) {
                        builder.append(0);
                    } else {
                        if (graterThanL > r) {
                            builder.append(0);
                        } else {
                            builder.append(1);
                        }
                    }
                }
            } else {
                builder.append(0);
            }
        }
        System.out.println(builder.toString());
    }
}
