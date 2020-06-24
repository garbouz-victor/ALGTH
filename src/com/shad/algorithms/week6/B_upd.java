package com.shad.algorithms.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class B_upd {
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

    private static class Graph {

        private ArrayList<List<Edge>> edges;
        private TreeSet<Vertex> vertexes;
        private HashSet<Integer> watched;
        private long[] dist;
        int n;

        private static class Vertex implements Comparable<Vertex>{
            int v;
            long dist;

            public Vertex(int v, long dist) {
                this.v = v;
                this.dist = dist;
            }

            @Override
            public int compareTo(Vertex other) {
                if (this.v == other.v) {
                    if (other.dist < this.dist) {
                        return 0;
                    }
                }
                if (this.dist > other.dist) {
                    return 1;
                } else if (this.dist == other.dist) {
                    if (this.v > other.v) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        }

        private static class Edge {
            int v1;
            int v2;
            int weight;

            public Edge(int v1, int v2, int weight) {
                this.v1 = v1;
                this.v2 = v2;
                this.weight = weight;
            }
        }

        public Graph(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            this.n = scanner.nextInt();
            int m = scanner.nextInt();
            edges = new ArrayList<>(n+1);
            vertexes = new TreeSet<>();
            watched = new HashSet<>();
            edges.add(Collections.EMPTY_LIST);
            dist = new long[n+1];
            dist[0] = -1;
            for (int i = 1; i <= n; i++) {
                edges.add(new ArrayList<>());
                if (i == 1) {
                    vertexes.add(new Vertex(i, 0));
                    dist[i] = 0;
                } else {
//                    vertexes.add(new Vertex(i, Long.MAX_VALUE));
                    dist[i] = Long.MAX_VALUE;
                }
            }
            for (int i = 0; i < m; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int weight = scanner.nextInt();
                edges.get(from).add(new Edge(from, to, weight));
                edges.get(to).add(new Edge(to, from ,weight));
            }
        }

        public void solve() {
            for (int i = 1; i <= n; i++) {
                Vertex v = vertexes.pollFirst();
                while (watched.contains(v.v)) {
                    v = vertexes.pollFirst();
                }
                watched.add(v.v);
                for (Edge e : edges.get(v.v)) {
                    if (dist[e.v2] > dist[e.v1] + e.weight) {
                        dist[e.v2] = dist[e.v1] + e.weight;
                    }
                    vertexes.add(new Vertex(e.v2, dist[e.v2]));
                }
            }
        }

        public long[] getAnswer() {
            return dist;
        }
    }

    public static void main(String... args) throws IOException {
        Graph g = new Graph(System.in);
        g.solve();
        long[] answer = g.getAnswer();
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < answer.length; i++) {
            builder.append(answer[i]).append(" ");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }
}
