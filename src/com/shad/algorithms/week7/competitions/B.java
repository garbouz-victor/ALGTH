package com.shad.algorithms.week7.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class B {
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
        int n,m;
        private static class Pair implements Comparable<Pair>{
            int first;
            int second;
            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }

            @Override
            public int compareTo(Pair other) {
                if (this.second > other.second) {
                    return 1;
                } else if (this.second == other.second) {
                    if (this.first > other.first) {
                        return 1;
                    } else if (this.first == other.first) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        }
        private ArrayList<List<Pair>> edges;
        private HashSet<Integer> unmarked;
        private ArrayList<Integer> dists;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            m = scanner.nextInt();
            edges = new ArrayList<>(n+1);
            edges.add(Collections.EMPTY_LIST);
            dists = new ArrayList<>(n+1);
            dists.add(Integer.MAX_VALUE);
            unmarked = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                edges.add(new ArrayList<>());
                unmarked.add(i);
                dists.add(Integer.MAX_VALUE);
            }
            for (int i = 0; i < m; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int weight = scanner.nextInt();
                edges.get(from).add(new Pair(to, weight));
                edges.get(to).add(new Pair(from, weight));
            }
        }
        public long solve(){
            long length = 0;
            TreeSet<Pair> front = new TreeSet<>();
            int start = unmarked.iterator().next();
            for (Pair to : edges.get(start)) {
                dists.set(to.first, to.second);
                front.add(new Pair(to.first, to.second));
            }
            unmarked.remove(start);
            Pair currentMin;
            for (int i = 1; i < n; i++) {
                currentMin = front.pollFirst();
                while (!unmarked.contains(currentMin.first)) {
                    currentMin = front.pollFirst();
                }
                for (Pair to : edges.get(currentMin.first)) {
                    if (unmarked.contains(to.first) && dists.get(to.first) > to.second) {
                        front.remove(new Pair(to.first, dists.get(to.first)));
                        dists.set(to.first, to.second);
                        front.add(new Pair(to.first, to.second));
                    }
                }
                unmarked.remove(currentMin.first);
                length += currentMin.second;
            }
            return length;
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.solve());
    }
}
