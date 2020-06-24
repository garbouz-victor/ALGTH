package com.shad.algorithms.week7.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

    private static class Solver {
        int n,m;
        int[] parents, ranks, experience;
        StringBuilder answer;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            n = scanner.nextInt();
            m = scanner.nextInt();
            parents = new int[n+1];
            ranks = new int[n+1];
            experience = new int[n+1];
            for (int i = 1; i <= n ; i++) {
                parents[i] = i;
            }
            answer = new StringBuilder();
            for (int i = 0; i < m; i++) {
                String[] commands = scanner.nextLine().split(" ");
                if ("add".equals(commands[0])) {
                    addExperince(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
                } else if ("join".equals(commands[0])) {
                    union(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
                } else {
                    answer.append(getExperience(Integer.parseInt(commands[1]))).append("\n");
                }
            }
            if (answer.length() > 0) {
                answer.deleteCharAt(answer.length() - 1);
            }
        }

        private int get(int v) {
            while (v != parents[v]) {
                v = parents[v];
            }
            return v;
        }

        private void union(int u, int v) {
            int parentU = get(u);
            int parentV = get(v);
            if (parentU == parentV) {
                return;
            }
            if (ranks[parentU] < ranks[parentV]) {
                int temp = parentU;
                parentU = parentV;
                parentV = temp;
            }
            parents[parentV] = parentU;
            if (ranks[parentU] == ranks[parentV]) {
                ranks[parentU] += 1;
            }
            experience[parentV] -= experience[parentU];
        }

        private void addExperince(int v, int val) {
            int parentV = get(v);
            experience[parentV] += val;
        }

        private int getExperience(int v) {
            int exp = experience[v];
            while (v != parents[v]) {
                v = parents[v];
                exp += experience[v];
            }
            return exp;
        }

        private String solve() {
            return answer.toString();
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.solve());
    }
}
