package com.shad.algorithms.week7.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

    private static class Solver {

        private static class Pair<T extends Comparable<T>,V extends Comparable<V>> implements Comparable<Pair<T,V>>{
            T first;
            V second;

            public Pair(T first, V second) {
                this.first = first;
                this.second = second;
            }

            @Override
            public int compareTo(Pair<T, V> other) {
                if (this.second.compareTo(other.second) == 1) {
                    return 1;
                } else if (this.second.compareTo(other.second) == 0) {
                    return this.first.compareTo(other.first);
                } else {
                    return -1;
                }
            }
        }

        HashMap<Integer, Pair<Integer, Integer>> coords;
        HashSet<Integer> unMarked;
        int N;

        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            coords = new HashMap<>();
            N = scanner.nextInt();
            unMarked = new HashSet<>();
            for (int i = 0; i < N; i++) {
                coords.put(i, new Pair<>(scanner.nextInt(), scanner.nextInt()));
                unMarked.add(i);
            }
        }

        public double solve() {
            TreeSet<Pair<Pair<Integer, Integer>, Long>> edges = new TreeSet<>();
            double length = 0.0;
            int vertex = unMarked.iterator().next();
            unMarked.remove(vertex);
            while (!unMarked.isEmpty()) {
                int x1 = coords.get(vertex).first;
                int y1 = coords.get(vertex).second;
                for (Integer i : unMarked) {
                    long x2 = coords.get(i).first;
                    long y2 = coords.get(i).second;
                    edges.add(new Pair<>(new Pair<>(vertex, i), (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
                }
                Pair<Pair<Integer, Integer>, Long> nextPoint = edges.pollFirst();
                while (!edges.isEmpty() && !unMarked.contains(nextPoint.first.second)) {
                    nextPoint = edges.pollFirst();
                }
                unMarked.remove(nextPoint.first.second);
                vertex = nextPoint.first.second;
                length += Math.sqrt(nextPoint.second);
            }
            return length;
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.solve());
    }
}
