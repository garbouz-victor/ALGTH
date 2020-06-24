package com.shad.algorithms.week5.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

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
    private static class Gamilton {
        int n,m;
        ArrayList<List<Integer>> graph;
        private HashSet<Integer> used;
        private ArrayList<Integer> sorted;
        public Gamilton(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            m = scanner.nextInt();
            graph = new ArrayList<>(n+1);
            graph.add(Collections.emptyList());
            for (int i = 1; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                int b = scanner.nextInt();
                int e = scanner.nextInt();
                graph.get(b).add(e);
            }
        }

        public String solve() {
            topSort();
            int answer = 0;
            int max;
            int[] d = new int[n+1];
            for(Integer i : sorted) {
                max = 0;
                for (Integer n : graph.get(i)) {
                    if (max < d[n]) {
                        max = d[n];
                    }
                }
                d[i] = max+1;
                if (answer < d[i]) {
                    answer = d[i];
                }
            }
            if (answer == n) {
                return "YES";
            } else {
                return "NO";
            }
        }

        private void topSort() {
            used = new HashSet<>();
            sorted = new ArrayList<>(n+1);
            sorted.add(0);
            for (int i = 1; i <= n; i++) {
                if (!used.contains(i)) {
                    dfs(i);
                }
            }
        }

        private void dfs(int v) {
            used.add(v);
            for (Integer n : graph.get(v)) {
                if (!used.contains(n)) {
                    dfs(n);
                }
            }
            sorted.add(v);
        }
    }

    public static void main(String... args) throws IOException {
        Gamilton g = new Gamilton(System.in);
        System.out.println(g.solve());
    }
}
