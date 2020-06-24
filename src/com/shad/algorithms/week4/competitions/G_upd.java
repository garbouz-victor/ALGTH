package com.shad.algorithms.week4.competitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G_upd {
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
        int[][] A;
        public Solver(InputStream is) throws IOException {
            FastScanner scanner = new FastScanner(is);
            S = scanner.nextInt();
            n = scanner.nextInt();
            w = new int[n+1];
            for (int i = 1; i <= n; i++) {
                w[i] = scanner.nextInt();
            }
        }

        private int buildMatrix() {
            int answer = 0;
            A = new int[n+1][];
            A[0] = new int[S+1];
            for (int i = 1; i <= n; i++) {
                A[i] = new int[S+1];
                for (int j = 0; j <= S ; j++) {
                    A[i][j] = A[i-1][j];
                    if (j >= w[i] && A[i-1][j-w[i]] + w[i] > A[i][j]) {
                        A[i][j] = A[i-1][j-w[i]] + w[i];
                        if (answer < A[i][j]) {
                            answer = A[i][j];
                        }
                    }
                }
            }
            return answer;
        }
    }

    public static void main(String... args) throws IOException {
        Solver s = new Solver(System.in);
        System.out.println(s.buildMatrix());
    }
}
