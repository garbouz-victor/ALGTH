package com.shad.algorithms.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class F {
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
        private static class Pair implements Comparable<Pair>{
            int to, weightNum;
            public Pair(int to, int weightNum) {
                this.to = to;
                this.weightNum = weightNum;
            }

            @Override
            public int compareTo(Pair other) {
//                if (this.to == other.to) {
//                    return 0;
//                }
                if (this.weightNum > other.weightNum) {
                    return 1;
                } else if (this.weightNum == other.weightNum) {
                    if (this.to > other.to) {
                        return 1;
                    } else if (this.to == other.to) {
                        return 0;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        }
        int n,m;
        private int[] weights;
        private ArrayList<List<Pair>> vertexes;
        public Graph(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            m = scanner.nextInt();
            weights = new int[m+1];
            vertexes = new ArrayList<>(n+1);
            vertexes.add(Collections.EMPTY_LIST);
            for (int i = 1; i <= n; i++) {
                vertexes.add(new ArrayList<>());
            }
            for (int i = 1; i <= m; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int weight = scanner.nextInt();
                weights[i] = weight;
                vertexes.get(from).add(new Pair(to, i));
                vertexes.get(to).add(new Pair(from, i));
            }
        }

        public int solve() {
            HashSet<Integer> seen = new HashSet<>();
            HashMap<Integer, Integer> parents = new HashMap<>();
            int[] d = new int[n+1];
            for (int i = 2; i <= n; i++) {
                d[i] = Integer.MAX_VALUE;
            }
            TreeSet<Pair> front = new TreeSet<>();
            front.add(new Pair(1,0));
            for (int i = 0; i < n; i++) {
                Pair min = front.pollFirst();
                while(vertexes.get(min.to) == null || seen.contains(min.to)) {
                    min = front.pollFirst();
                }
                seen.add(min.to);
                for(Pair v : vertexes.get(min.to)){
                    if (seen.contains(v.to)) {
                        continue;
                    }
                    int len = weights[v.weightNum];
                    if (d[v.to] > d[min.to] + len) {
                        d[v.to] = d[min.to] + len;
                        parents.put(v.to, min.to);
                        front.add(new Pair(v.to, d[v.to]));
                    }
                }
            }
            ArrayList<Integer> changingWeights = new ArrayList<>();
            int currentVertex = n;
            int counter = 0;
            while (currentVertex != 1) {
                if (counter > n) {
                    throw new RuntimeException("something bad happened!");
                }
                int next = parents.get(currentVertex);
                for(Pair v : vertexes.get(currentVertex)) {
                    if (v.to == next) {
                        changingWeights.add(v.weightNum);
                        break;
                    }
                }
                counter++;
                currentVertex = next;
            }
            int initAnswer = d[n];
            int maxDif = 0;
            for (Integer weightNum : changingWeights) {
                weights[weightNum] *= 2;
                seen = new HashSet<>();
                d = new int[n+1];
                for (int i = 2; i <= n; i++) {
                    d[i] = Integer.MAX_VALUE;
                }
                front = new TreeSet<>();
                front.add(new Pair(1,0));
                for (int i = 0; i < n; i++) {
                    Pair min = front.pollFirst();
                    while(vertexes.get(min.to) == null || seen.contains(min.to)) {
                        min = front.pollFirst();
                    }
                    seen.add(min.to);
                    for(Pair v : vertexes.get(min.to)){
                        if (seen.contains(v.to)) {
                            continue;
                        }
                        int len = weights[v.weightNum];
                        if (d[v.to] > d[min.to] + len) {
                            d[v.to] = d[min.to] + len;
                            front.add(new Pair(v.to, d[v.to]));
                        }
                    }
                }
                if (maxDif < d[n] - initAnswer) {
                    maxDif = d[n] - initAnswer;
                }
                weights[weightNum] /= 2;
            }
            return maxDif;
        }
    }

    public static void main(String... args) throws IOException {
        Graph g = new Graph(System.in);
        System.out.println(g.solve());
    }
}
