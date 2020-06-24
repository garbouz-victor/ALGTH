package com.shad.algorithms.week5.competitions;

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

    private static class Graph{

        private TreeMap<Integer, List<Integer>> edges;
        private TreeMap<Integer, Integer> marked;

        public Graph(int vertexCount) {
            this.edges = new TreeMap<>();
            for (int i = 1; i <= vertexCount ; i++) {
                edges.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int v1, int v2) {
            edges.get(v1).add(v2);
            edges.get(v2).add(v1);
        }

        public int countComponents() {
            marked = new TreeMap<>();
            int count = 0;
            for (Integer vertex : edges.keySet()) {
                if (marked.get(vertex) == null) {
                    count++;
                    dfs(vertex, count);
                }
            }
            return count;
        }

        private void dfs(int v, int mark) {
            marked.put(v, mark);
            for (Integer vertex : edges.get(v)) {
                if (marked.get(vertex) != null) {
                    continue;
                } else {
                    dfs(vertex, mark);
                }
            }
        }
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Graph g = new Graph(n);
        for (int i = 0; i < m; i++) {
            g.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        System.out.println(g.countComponents());
        StringBuilder builder = new StringBuilder();
        for (Integer vertex : g.marked.keySet()) {
            builder.append(g.marked.get(vertex)).append(" ");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }
}
