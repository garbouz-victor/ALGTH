package com.shad.algorithms.week7.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

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

    private static class Solver {

        private int n,m,k;
        int[] parents;
        ArrayList<String> requests;

        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            m = scanner.nextInt();
            k = scanner.nextInt();
            parents = new int[n+1];
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
            }
            for (int i = 0; i < m; i++) {
                scanner.nextLine();
            }
            requests = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                requests.add(scanner.nextLine());
            }
        }

        public String solve() {
            StringBuilder builder = new StringBuilder();
            for (int i = k-1; i >= 0; i--) {
                String[] r = requests.get(i).split(" ");
                if ("ask".equals(r[0])) {
                    int pU = get(Integer.parseInt(r[1]));
                    int pV = get(Integer.parseInt(r[2]));
                    if (pU == pV) {
                        builder.append("SEY").append("\n");
                    } else {
                        builder.append("ON").append("\n");
                    }
                } else {
                    union(Integer.parseInt(r[1]), Integer.parseInt(r[2]));
                }
            }
            return builder.reverse().toString().trim();
        }

        private void union(int u, int v) {
            int parentU = get(u);
            int parentV = get(v);
            parents[parentU] = parentV;
        }

        private int get(int v) {
            ArrayList<Integer> vertexes = new ArrayList<>();
            while (parents[v] != v) {
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
        System.out.println(s.solve());
    }
}
