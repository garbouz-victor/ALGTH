package com.shad.algorithms.week7.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class D {
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

    private static class Solver{
        private static class Edge implements Comparable<Edge>{
            int b,e,w;

            public Edge(int b, int e, int w) {
                this.b = b;
                this.e = e;
                this.w = w;
            }

            @Override
            public int compareTo(Edge other) {
                if (this.w > other.w) {
                    return 1;
                } else if (this.w == other.w) {
                    return 0;
                } else {
                    return -1;
                }
            }
        }
        private Edge[] edges;
        int n,m;
        private int[] parents;
        private int[] ranks;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            m = scanner.nextInt();
            edges = new Edge[m];
            parents = new int[n+1];
            ranks = new int[n+1];
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
            }
            for (int i = 0; i < m; i++) {
                edges[i] = new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            }
        }

        public String solve() {
            if (edges.length == 0) {
                return "NO";
            }
            Arrays.sort(edges);
            int min, max, counter = 0, minMax = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                if (i+n-1 > m) {
                    break;
                }
                min = edges[i].w;
                for (int j = i; j < m; j++) {
                    Edge e = edges[j];
                    int parentB = get(e.b);
                    int parentE = get(e.e);
                    if (parentB != parentE) {
                        union(e.b, e.e);
                        counter++;
                        if (counter == n-1) {
                            max = e.w;
                            if (minMax > max-min) {
                                minMax = max-min;
                            }
                            break;
                        }
                    }
                }
                if (counter < n-1) {
                    break;
                }
                counter = 0;
                for (int k = 1; k <= n; k++) {
                    parents[k] = k;
                    ranks[k] = 0;
                }
            }
            if (minMax != Integer.MAX_VALUE) {
                return "YES\n" + minMax;
            } else {
                return "NO";
            }
        }

        private int get(int v) {
            ArrayList<Integer> vertexes = new ArrayList<>();
            while (parents[v] != v) {
                vertexes.add(v);
                v = parents[v];
            }
            for (Integer subV : vertexes) {
                parents[subV] = v;
            }
            return v;
        }

        private void union(int v, int u) {
            int parentU = parents[u];
            int parentV = parents[v];
            if (ranks[parentU] > ranks[parentV]) {
                int temp = parentU;
                parentU = parentV;
                parentV = temp;
            }
            parents[parentU] = parentV;
            if (ranks[parentU] == ranks[parentV]) {
                ranks[parentV] += 1;
            }
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.solve());
    }
}
