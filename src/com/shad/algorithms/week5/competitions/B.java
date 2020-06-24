package com.shad.algorithms.week5.competitions;

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

    private static class Graph{

        private static class Pair {
            int first;
            int second;

            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }
        }

        private ArrayList<List<Integer>> edges;
        private ArrayList<Pair> marked;
        private ArrayList<Integer> answer = new ArrayList<>();
        private boolean circleFound;

        public Graph(int vertexCount) {
            this.edges = new ArrayList<>(vertexCount+1);
            for (int i = 0; i <= vertexCount ; i++) {
                edges.add(i, new ArrayList<>());
            }
        }

        public void addEdge(int v1, int v2) {
            edges.get(v1).add(v2);
        }

        public String getCircle() {
            marked = new ArrayList<>(edges.size());
            for (int i = 0; i < edges.size(); i++) {
                marked.add(null);
            }
            circleFound = false;
            int count = 0;
            for (int vertex = 1; vertex < edges.size(); vertex++) {
                if (marked.get(vertex) == null) {
                    count++;
                    if (dfs(vertex, count)) {
                        StringBuilder builder = new StringBuilder();
                        for (int i = answer.size() - 1; i >=0 ; i--) {
                            builder.append(answer.get(i)).append(" ");
                        }
                        if (builder.length() > 0) {
                            builder.deleteCharAt(builder.length() - 1);
                        }
                        return builder.toString();
                    }
                }
            }
            return null;
        }

        private boolean dfs(int v, int mark) {
            marked.set(v, new Pair(mark, 1));
            for (Integer vertex : edges.get(v)) {
                if (marked.get(vertex) != null && marked.get(vertex).first == mark && marked.get(vertex).second == 1) {
                    answer.add(vertex);
                    marked.get(vertex).second = 3;
                    return true;
                } else {
                    if (marked.get(vertex) == null && dfs(vertex, mark)) {
                        if (marked.get(vertex).second == 3) {
                            circleFound = true;
                        }
                        if (!circleFound) {
                            answer.add(vertex);
                        }
                        return true;
                    }
                }
            }
            marked.get(v).second = 2;
            return false;
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
        String circle = g.getCircle();
        if (circle == null) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(circle);
        }
    }
}
