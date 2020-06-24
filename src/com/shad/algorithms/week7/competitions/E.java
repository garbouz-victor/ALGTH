package com.shad.algorithms.week7.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class E {
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
    private static class Solver {
        int n,q;
        int[] parents;
        int[] ranks;
        TreeSet<Integer> front;
        String answer = null;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            q = scanner.nextInt();
            parents = new int[n+1];
            ranks = new int[n+1];
            front = new TreeSet<>();
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
                front.add(i);
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < q; i++) {
                int requestType = scanner.nextInt();
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                if (requestType == 1) {
                    if (u != v) {
                        union(u,v);
                    }
                } else if (requestType == 2) {
                    if (u == v) {
                        continue;
                    }
                    if (u > v) {
                        int temp = v;
                        v = u;
                        u = temp;
                    }
                    Integer after = front.higher(u);
                    while (after != null && after < v) {
                        union(u, after);
                        front.remove(after);
                        after = front.higher(after);
                    }
                    union(u,v);
                } else {
                    int parentU = get(u);
                    int parentV = get(v);
                    if (parentU == parentV) {
                        builder.append("YES").append("\n");
                    } else {
                        builder.append("NO").append("\n");
                    }
                }
            }
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            answer = builder.toString();
        }

        public String getAnswer() {
            return answer;
        }

        private void union(int u, int v) {
            int parentU = get(u);
            int parentV = get(v);
            if (ranks[parentU] < ranks[parentV]) {
                int temp = parentV;
                parentV = parentU;
                parentU = temp;
            }
            parents[parentV] = parentU;
//            if (front.contains(parentV)) {
//                front.remove(parentV);
//            }
            if (ranks[parentU] == ranks[parentV]) {
                ranks[parentU] += 1;
            }
        }

        private int get(int v) {
            ArrayList<Integer> vertexes = new ArrayList<>();
            while (v != parents[v]) {
                vertexes.add(v);
                v = parents[v];
            }
            for (Integer subV : vertexes) {
                parents[subV] = v;
            }
            return v;
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.getAnswer());
    }
}
