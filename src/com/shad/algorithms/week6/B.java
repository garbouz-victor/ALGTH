package com.shad.algorithms.week6;

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

    private static class Graph {

        private int n;

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
            int m = scanner.nextInt();
            edges = new ArrayList<>(n+1);
            dist = new long[n+1];
            dist[0] = 0;
            edges.add(Collections.emptyList());
            for (int i = 1; i <= n; i++) {
                edges.add(new ArrayList<>());
                dist[i] = -1;
            }
            for (int i = 0; i < m; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int weight = scanner.nextInt();
                edges.get(from).add(new Edge(to, weight));
                edges.get(to).add(new Edge(from, weight));
            }
        }

        public void updateWeights() {
            dist[1] = 0;
            Queue<Integer> queueOfVertexes = new LinkedList<>();
            queueOfVertexes.add(1);
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

        public long[] getAnswer() {
            return dist;
        }
    }

    public static void main(String... args) throws IOException {
        Graph g = new Graph(System.in);
        StringBuilder builder = new StringBuilder();
        g.updateWeights();
        long[] answer = g.getAnswer();
        for (int i = 1; i < answer.length; i++) {
            builder.append(answer[i]).append(" ");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }
}
