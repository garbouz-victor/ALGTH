package com.shad.algorithms.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

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

    private static class Graph {

        private int n;
        private int s;
        private int f;

        private ArrayList<List<Edge>> edges;
        private long[] dist;

        private static class Edge {
            private int vertex;
            private int weight;

            public Edge(int vertex, int weight) {
                this.vertex = vertex;
                this.weight = weight;
            }
        }

        public Graph(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            this.n = scanner.nextInt();
            this.s = scanner.nextInt();
            this.f = scanner.nextInt();
            edges = new ArrayList<>(n+1);
            dist = new long[n+1];
            dist[0] = 0;
            edges.add(Collections.emptyList());
            for (int i = 1; i <= n; i++) {
                edges.add(new ArrayList<>());
                dist[i] = -1;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int weight = scanner.nextInt();
                    if (i == j) {
                        continue;
                    }
                    if (weight == -1) {
                        continue;
                    }
                    edges.get(i).add(new Edge(j, weight));
                }
            }
        }

        public void updateWeights() {
            dist[s] = 0;
            Queue<Integer> queueOfVertexes = new LinkedList<>();
            queueOfVertexes.add(s);
            while (!queueOfVertexes.isEmpty()) {
                int v = queueOfVertexes.poll();
                for (Edge edge : edges.get(v)) {
                    if (dist[edge.vertex] == -1 || dist[edge.vertex] > dist[v] + edge.weight) {
                        dist[edge.vertex] = dist[v] + edge.weight;
                        queueOfVertexes.add(edge.vertex);
                    }
                }
            }
        }

        public long getAnswer() {
            return dist[f];
        }
    }

    public static void main(String... args) throws IOException {
        Graph g = new Graph(System.in);
        g.updateWeights();
        System.out.println(g.getAnswer());
    }
}
