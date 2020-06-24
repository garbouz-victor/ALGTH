package com.shad.algorithms.week5.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class C {
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

        private TreeMap<Integer, List<Integer>> edges;
        private TreeMap<Integer, Pair> marked;
//        private StringBuilder builder = new StringBuilder();
        private ArrayList<Integer> answer;

        public Graph(int vertexCount) {
            this.edges = new TreeMap<>();
            answer = new ArrayList<>();
            for (int i = 1; i <= vertexCount ; i++) {
                edges.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int v1, int v2) {
            edges.get(v1).add(v2);
        }

        public String sort() {
            marked = new TreeMap<>();
            int count = 0;
            for (Integer vertex : edges.keySet()) {
                if (marked.get(vertex) == null) {
                    count++;
                    if (dfs(vertex, count)) {
                        return null;
                    }
                }
            }
            StringBuilder builder = new StringBuilder();
            for (int i = answer.size() - 1; i >= 0 ; i--) {
                builder.append(answer.get(i)).append(" ");
            }
            //no circle
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }

        private boolean dfs(int v,/* int from,*/ int mark) {
            marked.put(v, new Pair(mark, 1));
            for (Integer vertex : edges.get(v)) {
//                if (vertex == from) {
//                    continue;
//                }
                if (marked.get(vertex) != null && marked.get(vertex).first == mark && marked.get(vertex).second == 1) {
                    return true;
                } else {
                    if (marked.get(vertex) == null && dfs(vertex, mark)) {
                        return true;
                    }
                }
            }
            marked.get(v).second = 2;
//            builder.append(v).append(" ");
            answer.add(v);
            return false;
        }
    }

    public static void main(String... args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        Graph g = new Graph(N);
        for (int i = 0; i < M; i++) {
            g.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        String result = g.sort();
        if (result == null) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}
