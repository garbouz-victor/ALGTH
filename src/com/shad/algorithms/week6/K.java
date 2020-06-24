package com.shad.algorithms.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class K {
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
        int N,M,K,S;
        private HashMap<Integer, ArrayList<Edge>> vertexes;
        private int[] answer;

        private static class Edge {
            int from,to,weight;
            public Edge(int from, int to, int weight) {
                this.from = from;
                this.to = to;
                this.weight = weight;
            }
        }

        public Graph(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            N = scanner.nextInt();
            M = scanner.nextInt();
            K = scanner.nextInt();
            S = scanner.nextInt();
            vertexes = new HashMap<>();
            for (int i = 0; i < M; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int weight = scanner.nextInt();
                if (!vertexes.containsKey(from)) {
                    vertexes.put(from, new ArrayList<>());
                }
                vertexes.get(from).add(new Edge(from, to, weight));
            }
        }

        public void findPathes() {
            HashMap<Integer, Integer> front = new HashMap<>();
            HashMap<Integer, Integer> previousFront;
            front.put(S, 0);
            for (int i = 0; i < K; i++) {
                previousFront = front;
                front = new HashMap<>();
                for (Integer v : previousFront.keySet()) {
                    if (!vertexes.containsKey(v)) {
                        continue;
                    }
                    for (Edge e : vertexes.get(v)) {
                        if (front.containsKey(e.to)) {
                            if (front.get(e.to) > previousFront.get(v) + e.weight) {
                                front.put(e.to, previousFront.get(v) + e.weight);
                            }
                        } else {
                            front.put(e.to, previousFront.get(v) + e.weight);
                        }
                    }
                }
            }
            answer = new int[N+1];
            answer[0] = 0;
            for (int i = 1; i <= N; i++) {
                if (front.containsKey(i)) {
                    answer[i] = front.get(i);
                } else {
                    answer[i] = -1;
                }
            }
        }

        public int[] getAnswer() {
            return answer;
        }
    }

    public static void main(String... args) throws IOException {
        Graph g = new Graph(System.in);
        g.findPathes();
        int[] answer = g.getAnswer();
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < answer.length; i++) {
            builder.append(answer[i]).append("\n");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        System.out.println(builder.toString());
    }
}
