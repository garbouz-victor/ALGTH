package com.shad.algorithms.week5.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

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

    private static class Graph {

        private static class Vertex {
            int i;
            int color;

            public Vertex(int i, int color) {
                this.i = i;
                this.color = color;
            }
        }

        private ArrayList<List<Integer>> edges;
        private ArrayList<Vertex> vertexes;
        private boolean error;

        public Graph(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            edges = new ArrayList<>(n+1);
            vertexes = new ArrayList<>(n+1);
            edges.add(Collections.EMPTY_LIST);
            vertexes.add(null);
            for (int i = 1; i <= n; i++) {
                edges.add(new ArrayList<>());
                vertexes.add(new Vertex(i, 0));
            }
            for (int i = 0; i < m; i++) {
                setEdge(scanner.nextInt(), scanner.nextInt());
            }
        }

        private void setEdge(int i, int j) {
            edges.get(i).add(j);
            edges.get(j).add(i);
        }

        public void color() {
            for (int i = 1; i < vertexes.size(); i++) {
                if (vertexes.get(i).color == 0) {
                    vertexes.get(i).color = 1;
                    dfs(i);
                    if (error) {
                        return;
                    }
                }
            }
        }

        private void dfs(int v) {
            if (error) {
                return;
            }
            int currentColor = vertexes.get(v).color;
            if (currentColor == 1) {
                for (Integer neighbor : edges.get(v)) {
                    if (vertexes.get(neighbor).color == currentColor) {
                        error = true;
                        return;
                    } else if (vertexes.get(neighbor).color == 0) {
                        vertexes.get(neighbor).color = 2;
                        dfs(neighbor);
                    }
                }
            } else if (currentColor == 2){
                for (Integer neighbor : edges.get(v)) {
                    if (vertexes.get(neighbor).color == currentColor) {
                        error = true;
                        return;
                    } else if (vertexes.get(neighbor).color == 0) {
                        vertexes.get(neighbor).color = 1;
                        dfs(neighbor);
                    }
                }
            }
        }
    }

    public static void main(String... args) throws IOException {
        Graph g = new Graph(System.in);
        g.color();
        if (g.error) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < g.vertexes.size(); i++) {
                builder.append(g.vertexes.get(i).color).append(" ");
            }
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            System.out.println(builder.toString());
        }
    }
}
