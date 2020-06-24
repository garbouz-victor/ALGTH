package com.shad.algorithms.week4.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G {
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
        int S,n;
        int[] w;
        ArrayList<ArrayList<Integer>> df;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            S = scanner.nextInt();
            n = scanner.nextInt();
            w = new int[n];
            for (int i = 0; i < n; i++) {
                w[i] = scanner.nextInt();
            }
        }

        public int buildDf() {
            df = new ArrayList<>(n);
            int answer = 0;
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> portion = new ArrayList<>();
                for (ArrayList<Integer> previous : df) {
                    for (Integer val : previous) {
                        if (val + w[i] <= S) {
                            portion.add(val + w[i]);
                            if (answer <= val + w[i]) {
                                answer = val + w[i];
                            }
                        }
                    }
                }
                df.add(portion);
                df.get(i).add(w[i]);
            }
            return answer;
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.buildDf());
    }
}
