package com.shad.algorithms.week7.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class A_upd {
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
        static class Pair {
            int first;
            int second;
            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }
        int N;
        ArrayList<Pair> coords;
        ArrayList<Integer> weights;
        HashSet<Integer> unMarked;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            N = scanner.nextInt();
            coords = new ArrayList<>(N);
            weights = new ArrayList<>(N);
            unMarked = new HashSet<>();
            for (int i = 0; i < N; i++) {
                coords.add(new Pair(scanner.nextInt(), scanner.nextInt()));
                weights.add(Integer.MAX_VALUE);
                unMarked.add(i);
            }
        }

        public double solve() {
            double length = 0.0;
            int currentMinV = -1, previousMinV;
            for (int i = 0; i < N; i++) {
                previousMinV = currentMinV;
                currentMinV = -1;
                //get min from unmarked
                for (Integer j : unMarked) {
                    if (currentMinV == -1 || weights.get(j) < weights.get(currentMinV)) {
                        currentMinV = j;
                    }
                }
                unMarked.remove(currentMinV);
                if (previousMinV != -1) {
                    length += Math.sqrt(weights.get(currentMinV));
                }
                int x2 = coords.get(currentMinV).first;
                int y2 = coords.get(currentMinV).second;
                for (Integer j : unMarked) {
                    int x1 = coords.get(j).first;
                    int y1 = coords.get(j).second;
                    int weight = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
                    if (weights.get(j) > weight) {
                        weights.set(j, weight);
                    }
                }
            }
            return length;
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.solve());
    }
}
